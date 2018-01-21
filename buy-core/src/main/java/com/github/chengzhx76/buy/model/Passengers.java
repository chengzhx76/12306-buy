package com.github.chengzhx76.buy.model;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/20
 */
public class Passengers implements Serializable {

    private Boolean isExist;
    private String exMsg;
    private List<String> two_isOpenClick;
    private List<String> other_isOpenClick;
    private List<Passenger> normal_passengers;
    private List<String> dj_passengers;

    public Boolean getExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public List<String> getTwo_isOpenClick() {
        return two_isOpenClick;
    }

    public void setTwo_isOpenClick(List<String> two_isOpenClick) {
        this.two_isOpenClick = two_isOpenClick;
    }

    public List<String> getOther_isOpenClick() {
        return other_isOpenClick;
    }

    public void setOther_isOpenClick(List<String> other_isOpenClick) {
        this.other_isOpenClick = other_isOpenClick;
    }

    public List<Passenger> getNormal_passengers() {
        return normal_passengers;
    }

    public void setNormal_passengers(List<Passenger> normal_passengers) {
        this.normal_passengers = normal_passengers;
    }

    public List<String> getDj_passengers() {
        return dj_passengers;
    }

    public void setDj_passengers(List<String> dj_passengers) {
        this.dj_passengers = dj_passengers;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Passengers{");
        sb.append("isExist=").append(isExist);
        sb.append(", exMsg='").append(exMsg).append('\'');
        sb.append(", two_isOpenClick=").append(two_isOpenClick);
        sb.append(", other_isOpenClick=").append(other_isOpenClick);
        sb.append(", normal_passengers=").append(normal_passengers);
        sb.append(", dj_passengers=").append(dj_passengers);
        sb.append('}');
        return sb.toString();
    }
}
