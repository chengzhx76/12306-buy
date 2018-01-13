package com.github.chengzhx76.buy.model;

import com.github.chengzhx76.buy.utils.OperationType;

import java.io.IOException;

public class Response {

    private byte[] content;

    private String rawText;

    private OperationType operation;

    private boolean requestSuccess;

    private int statusCode;

    private boolean destroy;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getRawText() {
        if (OperationType.CAPTCHA_IMG.equals(operation)) {
            return "IMG";
        }
        try {
            return new String(content, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public OperationType getOperation() {
        return operation;
    }

    public Response setOperation(OperationType operation) {
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
