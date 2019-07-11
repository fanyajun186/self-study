package com.example.demo.util.exception;

/**
 * @description
 */
public class ParamValidateException extends RuntimeException {
    private static final long serialVersionUID = -1331934594584289592L;

    public ParamValidateException() {
        super();
    }

    public ParamValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParamValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamValidateException(String message) {
        super(message);
    }

    public ParamValidateException(Throwable cause) {
        super(cause);
    }
}
