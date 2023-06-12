package com.test.spring.framework.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
@RequestMapping("exception")
public class ExceptionController {

    @GetMapping("test/{id}")
    public String test(@PathVariable Integer id) {
        if (id.equals(2)) {
            throw new BizException(-1, "参数错误啦");
        }
        return "test";
    }

    /**
     * 调用接口将会抛出异常：java.lang.NumberFormatException，
     * 可以在GlobalExceptionHandler类中进行捕获处理
     * @return
     */
    @GetMapping("internal")
    public String testInternal() {
        Integer.parseInt("abc12");
        return "test";
    }
}
