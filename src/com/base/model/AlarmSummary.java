package com.base.model;

/**
 * Created by suvan on 2017/10/31.
 */
public class AlarmSummary {
    private Integer id;

    private String level;

    private String alarmtime;

    private Integer res_id;

    private Integer node_id;

    private String parameter;

    private String parameterdetail;

    private String alarm_config;

    private String readflag;

    private Integer total;

    public AlarmSummary() {
    }

    public AlarmSummary(Integer id, Integer total) {
        this.id = id;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(String alarmtime) {
        this.alarmtime = alarmtime;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public void setRes_id(Integer res_id) {
        this.res_id = res_id;
    }

    public Integer getNode_id() {
        return node_id;
    }

    public void setNode_id(Integer node_id) {
        this.node_id = node_id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getAlarm_config() {
        return alarm_config;
    }

    public void setAlarm_config(String alarm_config) {
        this.alarm_config = alarm_config;
    }

    public String getReadflag() {
        return readflag;
    }

    public void setReadflag(String readflag) {
        this.readflag = readflag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getParameterdetail() {
        return parameterdetail;
    }

    public void setParameterdetail(String parameterdetail) {
        this.parameterdetail = parameterdetail;
    }
}
