package com.xingyu.net;

import com.xingyu.base.BizException;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.Serializable;
import java.net.URISyntaxException;

public interface BaseMethod {

    public String getMethodUrl();
    public HttpRequestBase getRequest() throws BizException,URISyntaxException;

    public static abstract class JiheRequest implements Serializable {

    }

    public static abstract class JiheResponse implements Serializable {
        private String sc ;
        private String errorMsg;
        private Integer errcode;
        private String errmsg;

        public Integer getErrcode() {
            return errcode;
        }

        public void setErrcode(Integer errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }
}

