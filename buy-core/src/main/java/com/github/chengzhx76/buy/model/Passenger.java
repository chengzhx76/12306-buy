package com.github.chengzhx76.buy.model;


import java.io.Serializable;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/20
 */
public class Passenger implements Serializable {
    private String code;
    private String passenger_name;
    private String sex_code;
    private String sex_name;
    private String born_date;
    private String country_code;
    private String passenger_id_type_code;
    private String passenger_id_type_name;
    private String passenger_id_no;
    private String passenger_type;
    private String passenger_flag;
    private String passenger_type_name;
    private String mobile_no;
    private String phone_no;
    private String email;
    private String address;
    private String postalcode;
    private String first_letter;
    private String recordCount;
    private String total_times;
    private String index_id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getSex_code() {
        return sex_code;
    }

    public void setSex_code(String sex_code) {
        this.sex_code = sex_code;
    }

    public String getSex_name() {
        return sex_name;
    }

    public void setSex_name(String sex_name) {
        this.sex_name = sex_name;
    }

    public String getBorn_date() {
        return born_date;
    }

    public void setBorn_date(String born_date) {
        this.born_date = born_date;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getPassenger_id_type_code() {
        return passenger_id_type_code;
    }

    public void setPassenger_id_type_code(String passenger_id_type_code) {
        this.passenger_id_type_code = passenger_id_type_code;
    }

    public String getPassenger_id_type_name() {
        return passenger_id_type_name;
    }

    public void setPassenger_id_type_name(String passenger_id_type_name) {
        this.passenger_id_type_name = passenger_id_type_name;
    }

    public String getPassenger_id_no() {
        return passenger_id_no;
    }

    public void setPassenger_id_no(String passenger_id_no) {
        this.passenger_id_no = passenger_id_no;
    }

    public String getPassenger_type() {
        return passenger_type;
    }

    public void setPassenger_type(String passenger_type) {
        this.passenger_type = passenger_type;
    }

    public String getPassenger_flag() {
        return passenger_flag;
    }

    public void setPassenger_flag(String passenger_flag) {
        this.passenger_flag = passenger_flag;
    }

    public String getPassenger_type_name() {
        return passenger_type_name;
    }

    public void setPassenger_type_name(String passenger_type_name) {
        this.passenger_type_name = passenger_type_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getTotal_times() {
        return total_times;
    }

    public void setTotal_times(String total_times) {
        this.total_times = total_times;
    }

    public String getIndex_id() {
        return index_id;
    }

    public void setIndex_id(String index_id) {
        this.index_id = index_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Passenger{");
        sb.append("code='").append(code).append('\'');
        sb.append(", passenger_name='").append(passenger_name).append('\'');
        sb.append(", sex_code='").append(sex_code).append('\'');
        sb.append(", sex_name='").append(sex_name).append('\'');
        sb.append(", born_date='").append(born_date).append('\'');
        sb.append(", country_code='").append(country_code).append('\'');
        sb.append(", passenger_id_type_code='").append(passenger_id_type_code).append('\'');
        sb.append(", passenger_id_type_name='").append(passenger_id_type_name).append('\'');
        sb.append(", passenger_id_no='").append(passenger_id_no).append('\'');
        sb.append(", passenger_type='").append(passenger_type).append('\'');
        sb.append(", passenger_flag='").append(passenger_flag).append('\'');
        sb.append(", passenger_type_name='").append(passenger_type_name).append('\'');
        sb.append(", mobile_no='").append(mobile_no).append('\'');
        sb.append(", phone_no='").append(phone_no).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", postalcode='").append(postalcode).append('\'');
        sb.append(", first_letter='").append(first_letter).append('\'');
        sb.append(", recordCount='").append(recordCount).append('\'');
        sb.append(", total_times='").append(total_times).append('\'');
        sb.append(", index_id='").append(index_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
