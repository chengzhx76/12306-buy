package com.github.chengzhx76.buy.model;

import com.github.chengzhx76.buy.utils.OperationEnum;

public class Response {

    private String content;

    private OperationEnum operation;

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
}
