package com.test.spring.framework.log;

import com.alibaba.fastjson.JSONObject;
import com.test.spring.framework.log.entity.AuditLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import utils.BeanAndMapUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Slf4j
public class ControllerAspectLogConfig {

    private static final String[] IGNORE_METHOD_NAMES = {"upload", "download", "logout"};
    private static final List<Object> SIMPLE_JAVA_TYPE = Arrays.asList(Long.class, long.class, Integer.class, int.class,
            Double.class, double.class, String.class);

    // 定义一个切点
    @Pointcut("execution(public * com.test.java..controller..*.*(..))")
    public void pointCut() {}

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logEnd(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = needLog(joinPoint);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            if (null != request) {
                processSuccessLog(request, joinPoint, (MethodSignature)joinPoint.getSignature());
            }
        });
        completableFuture.exceptionally(e -> {
            log.warn("[aspect log] record success log error, ", e);
            return null;
        });
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void logEnd(JoinPoint joinPoint, Exception e) {
        HttpServletRequest request = needLog(joinPoint);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            if (request != null) {
                processErrorLog(request, joinPoint, (MethodSignature) joinPoint.getSignature(), e);
            }
        });
        completableFuture.exceptionally(exception -> {
            log.warn("[audit log] record failed log error, ", e);
            return null;
        });
    }

    public HttpServletRequest needLog(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (ra == null) {
            return null;
        }
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (isIgnoreMethod(request, methodSignature)) {
            return null;
        }
        return request;
    }

    private boolean isIgnoreMethod(HttpServletRequest request, MethodSignature methodSignature) {
        if ("GET".equals(request.getMethod())) {
            log.debug("[aspect log] ignore request method:{}", request.getMethod());
            return true;
        }
        if (request.getRequestURI().contains("feign")) {
            return true;
        }
        String methodName = methodSignature.getMethod().getName();
        log.info("method name:{}, {}", methodName, methodSignature.getName());
        for (String ignoreMethodName : IGNORE_METHOD_NAMES) {
            if (methodName.toLowerCase().contains(ignoreMethodName)) {
                return true;
            }
        }
        return false;
    }

    private void processSuccessLog(HttpServletRequest request, JoinPoint joinPoint, MethodSignature methodSignature) {
        AuditLog auditLog = createCommonLog(request, joinPoint, methodSignature);
        auditLog.setStatus("Successful");
        log.info("[audit success log] : {}", auditLog);
    }

    private void processErrorLog(HttpServletRequest request, JoinPoint joinPoint, MethodSignature methodSignature, Exception e) {
        AuditLog auditLog = createCommonLog(request, joinPoint, methodSignature);
        auditLog.setStatus("Failed");
//        if (e instanceof BusinessException) {
//            BusinessException businessException = (BusinessException) e;
//            auditLog.setFailReason(messageHandler.getMessage(request, businessException.getCode(),
//                    businessException.getDefaultMessage(), businessException.getArgs()));
//        } else {
            auditLog.setFailReason(e.getClass().toString());
//        }
        log.info("[audit error log] : {}", auditLog);
    }

    private AuditLog createCommonLog(HttpServletRequest request, JoinPoint joinPoint, MethodSignature methodSignature) {
        AuditLog log = new AuditLog();
//        setAction(log, methodSignature);
        log.setUrl(request.getRequestURI());
        log.setActionTime(System.currentTimeMillis());
        log.setIp("");

        String methodName = methodSignature.getMethod().getName();
        Object[] methodParamObjs = joinPoint.getArgs();
        String[] methodParamNames = methodSignature.getParameterNames();
        Map<String, Object> paramMap = parseMethodParameterObjs(methodParamObjs, methodParamNames);
        String email = paramMap.get("email") == null ? null : paramMap.get("email").toString();
        log.setLoginEmail(email);
        if (!StringUtils.equals("login", methodName)) {
            String reqParam = getParamAndRequestBody(methodParamObjs, methodParamNames);
            log.setReqParameter(reqParam);
            log.setReqBody(JSONObject.toJSONString(paramMap));
        }
        return log;
    }

    private String getParamAndRequestBody(Object[] methodParamObjs, String[] methodParamNames) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < methodParamObjs.length; i++) {
            Object methodParamObj = methodParamNames[i];
            if (!SIMPLE_JAVA_TYPE.contains(methodParamObj.getClass())
                    || AnnotationUtils.findAnnotation(methodParamObj.getClass(), PathVariable.class) == null) {
                continue;
            }
            sb.append(methodParamNames[i]).append("=")
                    .append(methodParamObjs.getClass().isPrimitive() ? methodParamObj : methodParamObj.toString())
                    .append(",");
        }
        return StringUtils.removeEndIgnoreCase(sb.toString(), ",");
    }

    private Map<String, Object> parseMethodParameterObjs(Object[] methodParameterObjs, String[] methodParameterNames) {
        if (methodParameterObjs == null || methodParameterObjs.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, Object> paramObj = new HashMap<>();
        for (int i = 0; i < methodParameterObjs.length; i++) {
            Object obj = methodParameterObjs[i];
            if (obj instanceof HttpServletResponse || obj instanceof HttpSession || obj instanceof BindingResult
                    || obj instanceof MultipartFile) {
                continue;
            }
            if (obj instanceof HttpServletRequest) {
                parseHttpServletRequest(paramObj, obj);
            } else if (SIMPLE_JAVA_TYPE.contains(obj.getClass())) {
                parseSimpleClassType(paramObj, methodParameterNames[i], obj);
            } else if (obj instanceof Map) {
                parseMap(paramObj, obj);
            } else if (obj instanceof Collection) {
                parseCollection(paramObj, methodParameterNames[i], obj);
            } else {
                parseCustomBean(paramObj, obj);
            }
        }
        return paramObj;
    }

    /**
     * swagger或spring-doc
     */
//    private void setAction(AuditLog log, MethodSignature methodSignature) {
//        Method targetMethod = methodSignature.getMethod();
//        if (targetMethod.isAnnotationPresent(ApiOperation.class)) {
//            ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
//            log.setAction(apiOperation.value());
//        }
//    }

    private void parseMap(Map<String, Object> paramObj, Object methodParameterObj) {
        Map map = (Map) methodParameterObj;
        paramObj.putAll(map);
    }

    private void parseCollection(Map<String, Object> paramObj, String methodParameterName, Object methodParameterObj) {
        Collection collection = (Collection) methodParameterObj;
        paramObj.put(methodParameterName, collection);
    }

    private void parseSimpleClassType(Map<String, Object> paramObj, String methodParameterName, Object methodParameterObj) {
        paramObj.put(methodParameterName, methodParameterObj.getClass().isPrimitive() ? methodParameterObj : methodParameterObj.toString());
    }

    private void parseHttpServletRequest(Map<String, Object> paramObj, Object methodParameterObj) {
        HttpServletRequest request = (HttpServletRequest) methodParameterObj;
        Map<String, String[]> paraMap = request.getParameterMap();
        paramObj.putAll(paraMap);
    }
    private void parseCustomBean(Map<String, Object> paramObj, Object obj) {
        Map<String, Object> parseMap = BeanAndMapUtils.objectToMap(obj);
        if (parseMap == null || parseMap.size() == 0) {
            return;
        }
        paramObj.putAll(parseMap);
    }
}
