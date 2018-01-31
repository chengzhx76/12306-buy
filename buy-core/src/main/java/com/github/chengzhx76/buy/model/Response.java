package com.github.chengzhx76.buy.model;

import com.github.chengzhx76.buy.utils.OperationType;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Response implements Serializable {

    private byte[] content;

    private String rawText;

    private OperationType operation;

    private boolean requestSuccess;

    private int statusCode;

    private Set<Cookie> cookies = new HashSet<>();


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
            setRawText(new String(content, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return rawText;
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

    public Response addCookie(String name, String value, String domain, String path) {
        cookies.add(new Cookie(name, value, domain, path));
        return this;
    }

    public Set<Cookie> getCookies() {
        return cookies;
    }
}
