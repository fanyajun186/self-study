package com.example.demo.util.spel.simba;


public class ValidateException extends RuntimeException {

    /**
     * 错误信息
     */
    private String errMsg;

    public ValidateException(String errMsg) {
        super(errMsg, null, true, false);
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
