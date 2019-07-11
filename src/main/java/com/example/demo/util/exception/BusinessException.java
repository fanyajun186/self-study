package com.example.demo.util.exception;

/**
 * 自定义业务异常
 * @author: yajun.fan
 * @date: 2019年4月29日
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -3349795398240228742L;

    /**
     * 错误信息
     */
    private String errMsg;
    
    /**
     * 订单号
     */
    private String appCode;
    
    /**
     * 请求参数
     */
    private String param;
    


    public BusinessException(String errMsg,String appCode, String param) {
        super(errMsg, null, true, false);
        this.errMsg = errMsg;
        this.appCode = appCode;
        this.param = param;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }        
    
    public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public static void propagate(String errMsg, String param,String errCause) {
        throw new BusinessException(errMsg,param,errCause);
    }
}
