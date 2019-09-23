package com.java.app.loan.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.java.app.loan.enums.ErrorCode;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorBaseRS {
    private String message;
    private String type;
    private String code;
    private String errorSubCode;
    private String errorUserTitle;
    private String errorUserMsg;
    private String traceId;
    private String debugMessage;

    public String getMessage() {
        return message;
    }

    public ErrorBaseRS setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getType() {
        return type;
    }

    public ErrorBaseRS setType(String type) {
        this.type = type;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ErrorBaseRS setCode(String code) {
        this.code = code;
        return this;
    }

    public String getErrorSubCode() {
        return errorSubCode;
    }

    public ErrorBaseRS setErrorSubCode(String errorSubCode) {
        this.errorSubCode = errorSubCode;
        return this;
    }

    public ErrorBaseRS setErrorBasedByEnumError(ErrorCode errorCode)
    {
        this.code = errorCode.getCode();
        this.message = errorCode.getValue();
        return this;
    }

    public String getErrorUserTitle() {
        return errorUserTitle;
    }

    public ErrorBaseRS setErrorUserTitle(String errorUserTitle) {
        this.errorUserTitle = errorUserTitle;
        return this;
    }

    public String getErrorUserMsg() {
        return errorUserMsg;
    }

    public ErrorBaseRS setErrorUserMsg(String errorUserMsg) {
        this.errorUserMsg = errorUserMsg;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public ErrorBaseRS setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public ErrorBaseRS setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
        return this;
    }
}
