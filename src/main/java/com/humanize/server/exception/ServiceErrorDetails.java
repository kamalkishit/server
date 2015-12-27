package com.humanize.server.exception;

public class ServiceErrorDetails {

    private String errorCode;
    private String errorMsg;
    
    public ServiceErrorDetails() {
    }
    
    public ServiceErrorDetails(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
