package com.example.demo.util.exception;

/**
 * http连接一次
 *
 */
public class HttpException extends RuntimeException {
    private static final long serialVersionUID = -1082076485996654822L;

    /**
     * 错误码值
     */
    private String errCode;

    private String url;

    private Long cost;

    private String employeeId;

    private Object param;

    private String result;

    private String reason;

    public HttpException(String errCode, String url, Long cost, String employeeId, Object param, String result,String reason,
                         Throwable cause) {
        super(cause.getMessage(), cause, true, false);
        this.errCode = errCode;
        this.url = url;
        this.cost = cost;
        this.employeeId = employeeId;
        this.param = param;
        this.result = result;
        this.reason = reason;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public HttpException setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
