package com.github.chengzhx76.buy.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
@Deprecated
public class Ticket implements Serializable {
    private String flag;
    private Map<String, String> map;
    private List<String> result;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getResult() {
        return result;
    }

    public Ticket setResult(List<String> result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ticket{");
        sb.append("flag='").append(flag).append('\'');
        sb.append(", map=").append(map);
        sb.append(", result='").append(result).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
