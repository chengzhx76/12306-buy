package com.github.chengzhx76.buy.model;

import java.io.Serializable;
import java.util.List;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/18
 */
@Deprecated
public class SubmitOrder implements Serializable {

    private String validateMessagesShowId;
    private Boolean status;
    private Integer httpstatus;
    private List<String> messages;
    private Object validateMessages;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SubmitOrder{");
        sb.append("validateMessagesShowId='").append(validateMessagesShowId).append('\'');
        sb.append(", status=").append(status);
        sb.append(", httpstatus=").append(httpstatus);
        sb.append(", messages=").append(messages);
        sb.append(", validateMessages=").append(validateMessages);
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
