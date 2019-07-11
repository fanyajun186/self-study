package com.example.demo.util.exception;

public class ServiceRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1331934594584289592L;

    public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

}
