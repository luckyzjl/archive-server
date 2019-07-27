package com.xingyu.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xingyu.base.BizException;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable{
    private static final String SC_SUCC = "0";

    private String sc;

    private T data;

    private String msg;

    private PageInfo pageinfo;

    public boolean successed(){
        return SC_SUCC.equals(sc);
    }

    public static void assertSuccess(ApiResponse response){
        if(null == response){
            throw new BizException("inter.service.call.fail","调用内部服务失败");
        }else if(!response.successed()){
            throw new BizException(response.sc,response.msg);
        }
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageInfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(PageInfo pageinfo) {
        this.pageinfo = pageinfo;
    }


    public ApiResponse() {
    }

    public ApiResponse(String code, String message, T data, PageInfo pageInfo) {
        this.sc = code;
        this.msg = message;
        this.data = data;
        this.pageinfo = pageInfo;
    }


    public static class PageInfo  implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer pageNo;
        private Integer pageSize;
        private Integer pageAmount;
        private Integer recordAmount;

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPageAmount() {
            return pageAmount;
        }

        public void setPageAmount(Integer pageAmount) {
            this.pageAmount = pageAmount;
        }

        public Integer getRecordAmount() {
            return recordAmount;
        }

        public void setRecordAmount(Integer recordAmount) {
            this.recordAmount = recordAmount;
        }
    }
}
