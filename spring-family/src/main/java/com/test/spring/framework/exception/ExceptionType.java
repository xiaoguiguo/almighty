package com.test.spring.framework.exception;

public enum ExceptionType {
    // 错误定义
    SUCCESS(2000, "成功!"),
    BODY_NOT_MATCH(4000,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(4001,"请求的数字签名不匹配!"),
    NOT_FOUND(4004, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(5000, "服务器内部错误!"),
    SERVER_BUSY(5003,"服务器正忙，请稍后再试!");

    final int code;
    final String result;

    ExceptionType(Integer code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

}
