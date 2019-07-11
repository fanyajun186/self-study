package com.example.demo.util.exception;

/**
 * 外部系统调用异常
 */
public class InvokeException extends RuntimeException {
    private static final long serialVersionUID = 3050322133322939756L;

    /**
     * 业务错误码值
     */
    private String businessErrorCode;

    /**
     * 错误码值
     */
    private String errCode;

    /**
     * 订单编号
     */
    private String appCode;

    /**
     * 销售Id
     */
    private String employeeId;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 返回数据
     */
    private String result;
    /**
     * 错误信息
     */
    private String errMsg;
    
    /**
     * 异常原因
     */
    private String errCause;

    public InvokeException(String businessErrorCode, String errCode, String appCode, String employeeId, String param,
                           String result, String errMsg, Throwable cause, String errCause) {
        super(errMsg, cause, true, false);
        this.businessErrorCode = businessErrorCode;
        this.errCode = errCode;
        this.appCode = appCode;
        this.employeeId = employeeId;
        this.param = param;
        this.result = result;
        this.errMsg = errMsg;
        this.errCause = errCause;
    }

    public String getBusinessErrorCode() {
        return businessErrorCode;
    }

    public void setBusinessErrorCode(String businessErrorCode) {
        this.businessErrorCode = businessErrorCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public InvokeException setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

	public String getErrCause() {
		return errCause;
	}

	public void setErrCause(String errCause) {
		this.errCause = errCause;
	}    
    
}
