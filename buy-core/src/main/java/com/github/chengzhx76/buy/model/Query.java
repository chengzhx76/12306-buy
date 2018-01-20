package com.github.chengzhx76.buy.model;

import java.io.Serializable;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
@Deprecated
public class Query implements Serializable {
    private Ticket data;
    private Integer httpstatus;
    private String message;
    private Boolean status;

    public Ticket getData() {
        return data;
    }

    public void setData(Ticket data) {
        this.data = data;
    }

    public Integer getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(Integer httpstatus) {
        this.httpstatus = httpstatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Query{");
        sb.append("data=").append(data);
        sb.append(", httpstatus=").append(httpstatus);
        sb.append(", message='").append(message).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
