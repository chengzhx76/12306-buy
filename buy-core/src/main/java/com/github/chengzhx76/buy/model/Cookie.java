package com.github.chengzhx76.buy.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    public Cookie(String name, String value) {
        this(name, value,null);
    }

    public Cookie(String name, String value, String domain) {
        this(name, value, domain,null);
    }

    public Cookie(String name, String value, String domain, String path) {
        this(name, value, domain, path,null);
    }

    public Cookie(String name, String value, String domain, String path, Date expiryDate) {
        this.name = name;
        this.value = value;
        this.domain = domain;
        this.path = path;
        this.expiryDate = expiryDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cookie cookie = (Cookie) o;

        return new EqualsBuilder()
                .append(name, cookie.name)
                .append(value, cookie.value)
                .append(expiryDate, cookie.expiryDate)
                .append(domain, cookie.domain)
                .append(path, cookie.path)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(value)
                .append(expiryDate)
                .append(domain)
                .append(path)
                .toHashCode();
    }
}
