package com.github.chengzhx76.buy.model;

import com.github.chengzhx76.buy.utils.Flag;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 * {"validateMessagesShowId":"_validatorMessage","status":true,
 * "httpstatus":200,"data":{"flag":false},"messages":[],"validateMessages":{}}
 */
public class ValidateMsg implements Serializable {

    private String validateMessagesShowId;
    private Boolean status;
    private Integer httpstatus;
    private List<String> messages;
    private Object validateMessages;
    private Flag data;

    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(Integer httpstatus) {
        this.httpstatus = httpstatus;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Object getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(Object validateMessages) {
        this.validateMessages = validateMessages;
    }

    public Flag getData() {
        return data;
    }

    public void setData(Flag data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValidateMsg{");
        sb.append("validateMessagesShowId='").append(validateMessagesShowId).append('\'');
        sb.append(", status=").append(status);
        sb.append(", httpstatus=").append(httpstatus);
        sb.append(", messages=").append(messages);
        sb.append(", validateMessages=").append(validateMessages);
        sb.append('}');
        return sb.toString();
    }
}
