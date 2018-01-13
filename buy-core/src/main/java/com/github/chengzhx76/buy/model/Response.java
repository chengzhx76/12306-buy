package com.github.chengzhx76.buy.model;

import com.github.chengzhx76.buy.utils.OperationEnum;

public class Response {

    private String content;

    private OperationEnum operation;

    private boolean requestSuccess;

    private int statusCode;

    private boolean destroy;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public Response setOperation(OperationEnum operation) {
        this.operation = operation;
        return this;
    }

    public boolean isRequestSuccess() {
        return requestSuccess;
    }

    public Response setRequestSuccess(boolean requestSuccess) {
        this.requestSuccess = requestSuccess;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Response setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public void destroy() {
        destroy = true;
    }

    public boolean isDestroy() {
        return destroy;
    }
}
