package com.humanize.server.exception;

import java.util.List;

public class ServiceError {

    private String errorId;

    private String requestId;

    private long timeStamp;

    private String reason;

    private List<ServiceErrorDetails> details;

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ServiceErrorDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ServiceErrorDetails> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ServiceError{" +
                "errorId='" + errorId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", timeStamp=" + timeStamp +
                ", reason='" + reason + '\'' +
                ", details=" + details +
                '}';
    }
}
