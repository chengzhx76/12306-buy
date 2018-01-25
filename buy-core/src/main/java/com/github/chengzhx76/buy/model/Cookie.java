package com.github.chengzhx76.buy.model;

import java.util.Date;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/25
 */
public class Cookie {
    private String name;
    private String value;
    private Date expiryDate;
    private String domain;
    private String path;

    public Cookie() {
    }

    public Cookie(String name, String value, String domain, String path) {
        this(name, value, null, domain, path);
    }

    public Cookie(String name, String value, Date expiryDate, String domain, String path) {
        this.name = name;
        this.value = value;
        this.expiryDate = expiryDate;
        this.domain = domain;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public Cookie setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Cookie setValue(String value) {
        this.value = value;
        return this;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Cookie setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public Cookie setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Cookie setPath(String path) {
        this.path = path;
        return this;
    }
}
