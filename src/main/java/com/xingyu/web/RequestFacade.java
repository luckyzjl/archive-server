package com.xingyu.web;

import com.alibaba.fastjson.JSON;
import com.xingyu.domain.vo.BizParameter;
import com.xingyu.domain.vo.ReqParameter;
import org.apache.http.util.TextUtils;

public class RequestFacade {

    private  static ThreadLocal<ReqParameter> threadLocal = new ThreadLocal<ReqParameter>();

    public static void setParameters(ReqParameter parameterInfo)
    {
        threadLocal.set(parameterInfo);
    }

    public static ReqParameter getParameters(){
        return threadLocal.get();
    }

    public static BizParameter getBizParameters(){
        ReqParameter reqParameter = threadLocal.get();
        BizParameter bizParameter = null;
        if(!TextUtils.isBlank(reqParameter.getDataJson())){
            bizParameter = JSON.parseObject(reqParameter.getDataJson(),BizParameter.class);
        }else if (!TextUtils.isBlank(reqParameter.getBodyJson())){
            bizParameter = JSON.parseObject(reqParameter.getBodyJson(), BizParameter.class);
        }else if(!TextUtils.isBlank(reqParameter.getParamJson())){
            bizParameter = JSON.parseObject(reqParameter.getParamJson(), BizParameter.class);
        }else{
            bizParameter = new BizParameter();
        }
        return bizParameter;
    }

    public static void remove(){
        threadLocal.remove();
    }
}
