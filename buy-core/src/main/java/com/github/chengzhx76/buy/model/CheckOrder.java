package com.github.chengzhx76.buy.model;

import java.io.Serializable;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/22
 */
@Deprecated
public class CheckOrder implements Serializable {
    private String ifShowPassCode;
    private String canChooseBeds;
    private String canChooseSeats;
    private String choose_Seats;
    private String isCanChooseMid;
    private String ifShowPassCodeTime;
    private Boolean submitStatus;
    private String smokeStr;

    public String getIfShowPassCode() {
        return ifShowPassCode;
    }

    public void setIfShowPassCode(String ifShowPassCode) {
        this.ifShowPassCode = ifShowPassCode;
    }

    public String getCanChooseBeds() {
        return canChooseBeds;
    }

    public void setCanChooseBeds(String canChooseBeds) {
        this.canChooseBeds = canChooseBeds;
    }

    public String getCanChooseSeats() {
        return canChooseSeats;
    }

    public void setCanChooseSeats(String canChooseSeats) {
        this.canChooseSeats = canChooseSeats;
    }

    public String getChoose_Seats() {
        return choose_Seats;
    }

    public void setChoose_Seats(String choose_Seats) {
        this.choose_Seats = choose_Seats;
    }

    public String getIsCanChooseMid() {
        return isCanChooseMid;
    }

    public void setIsCanChooseMid(String isCanChooseMid) {
        this.isCanChooseMid = isCanChooseMid;
    }

    public String getIfShowPassCodeTime() {
        return ifShowPassCodeTime;
    }

    public void setIfShowPassCodeTime(String ifShowPassCodeTime) {
        this.ifShowPassCodeTime = ifShowPassCodeTime;
    }

    public Boolean getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(Boolean submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getSmokeStr() {
        return smokeStr;
    }

    public void setSmokeStr(String smokeStr) {
        this.smokeStr = smokeStr;
    }
}
