package com.xingyu.domain.vo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class ReqParameter {
    private String data;
    private String dataJson;
    private String bodyJson;
    private String paramJson;
    private String action;
    private String useragent;
    private String location;
    private String host;
    private String clientrealip;
    private String queryString;

    private Integer pageNo;
    private Integer pageSize;

    private LoginTokenInfo loginTokenInfo;
    private String loginToken;


    private static Map<String, Field> fieldMap = new HashMap<>();

//    public static Map<String,Field> getFieldMap(){
//        return fieldMap;
//    }

    static {
        Field[] fields = ReqParameter.class.getDeclaredFields();
        for (Field field : fields) {
            fieldMap.put(field.getName().toLowerCase(),field);
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getBodyJson() {
        return bodyJson;
    }

    public void setBodyJson(String bodyJson) {
        this.bodyJson = bodyJson;
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientrealip() {
        return clientrealip;
    }

    public void setClientrealip(String clientrealip) {
        this.clientrealip = clientrealip;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Integer getPageNo() {
        return null == pageNo? 1:pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return null == pageSize? 10:pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public LoginTokenInfo getLoginTokenInfo() {
        return loginTokenInfo;
    }

    public void setLoginTokenInfo(LoginTokenInfo loginTokenInfo) {
        this.loginTokenInfo = loginTokenInfo;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public static Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public static void setFieldMap(Map<String, Field> fieldMap) {
        ReqParameter.fieldMap = fieldMap;
    }
}
