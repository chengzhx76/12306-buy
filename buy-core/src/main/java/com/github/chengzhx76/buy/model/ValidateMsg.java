package com.github.chengzhx76.buy.model;

import com.github.chengzhx76.buy.utils.Flag;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class ValidateMsg implements Serializable {

    private String validateMessagesShowId;
    private Boolean status;
    private Integer httpstatus;
    private List<String> messages;
    private Object validateMessages;
    private Flag data;

    private String result_message;
    private String result_code;

    private String uamtk;

    private String apptk;

    private String newapptk;

    private String username;

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

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getUamtk() {
        return uamtk;
    }

    public void setUamtk(String uamtk) {
        this.uamtk = uamtk;
    }

    public String getApptk() {
        return apptk;
    }

    public void setApptk(String apptk) {
        this.apptk = apptk;
    }

    public String getNewapptk() {
        return newapptk;
    }

    public void setNewapptk(String newapptk) {
        this.newapptk = newapptk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValidateMsg{");
        sb.append("validateMessagesShowId='").append(validateMessagesShowId).append('\'');
        sb.append(", status=").append(status);
        sb.append(", httpstatus=").append(httpstatus);
        sb.append(", messages=").append(messages);
        sb.append(", validateMessages=").append(validateMessages);
        sb.append(", data=").append(data);
        sb.append(", result_message='").append(result_message).append('\'');
        sb.append(", result_code='").append(result_code).append('\'');
        sb.append(", uamtk='").append(uamtk).append('\'');
        sb.append(", apptk='").append(apptk).append('\'');
        sb.append(", newapptk='").append(newapptk).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
