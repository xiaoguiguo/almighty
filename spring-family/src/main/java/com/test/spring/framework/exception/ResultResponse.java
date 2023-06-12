package com.test.spring.framework.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义数据传输
 * 返回数据格式 统一处理
 */
@Getter
@Setter
public class ResultResponse<T> {

    private int code;
    private String message = "success";
    private T data;

    public ResultResponse() {}

    public ResultResponse(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getResult();
    }

    public static ResultResponse success() {
        return success(null);
    }

    public static <D> ResultResponse<D> success(D data) {
        ResultResponse<D> resultResponse = new ResultResponse();
        resultResponse.setCode(ExceptionType.SUCCESS.getCode());
        resultResponse.setMessage(ExceptionType.SUCCESS.getResult());
        resultResponse.setData(data);
        return resultResponse;
    }

    /**
     * 失败
     */
    public static ResultResponse error(ExceptionType exceptionType) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(exceptionType.getCode());
        resultResponse.setMessage(exceptionType.getResult());
        resultResponse.setData(null);
        return resultResponse;
    }

    /**
     * 失败
     */
    public static ResultResponse error(int code, String errorMsg) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setMessage(errorMsg);
        resultResponse.setData(null);
        return resultResponse;
    }

    /**
     * 失败
     */
    public static ResultResponse error(String errorMsg) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(-1);
        resultResponse.setMessage(errorMsg);
        resultResponse.setData(null);
        return resultResponse;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
