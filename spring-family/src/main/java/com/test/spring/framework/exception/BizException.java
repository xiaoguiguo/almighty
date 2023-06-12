package com.test.spring.framework.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {

    protected int errorCode;
    protected String errorMsg;

    public BizException(ExceptionType exceptionType) {
        super(exceptionType.getResult());
        this.errorCode = exceptionType.getCode();
        this.errorMsg = exceptionType.getResult();
    }

    public BizException(ExceptionType exceptionType, Throwable cause) {
        super(exceptionType.getResult(), cause);
        this.errorCode = exceptionType.getCode();
        this.errorMsg = exceptionType.getResult();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
