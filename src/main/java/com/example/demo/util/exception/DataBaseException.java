package com.example.demo.util.exception;

/**
 * 数据库异常
 * @author yajun.fan
 * @date 2019年3月27日
 */

public class DataBaseException extends RuntimeException {
    private static final long serialVersionUID = -4958198729958384795L;

    /**
     * 错误码值
     */
    private String errCode;


    public DataBaseException(String errCode, Throwable cause) {
        super(errCode, cause, true, false);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

}
