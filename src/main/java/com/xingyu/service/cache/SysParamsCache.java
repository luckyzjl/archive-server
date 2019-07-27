package com.xingyu.service.cache;

import com.xingyu.mapper.SysParamsMapper;
import com.xingyu.domain.po.SysParams;
import com.xingyu.domain.po.SysParamsExample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-16
 */

@Scope("singleton")
@Service
public class SysParamsCache {
    private static Log logger = LogFactory.getLog(SysParamsCache.class);

    @Autowired
    SysParamsMapper sysParamsMapper;

    private Map<String, SysParams> sysParamsMap = new HashMap<String,SysParams>();

    @PostConstruct
    public Integer init() {
        List<SysParams> list = sysParamsMapper.selectByExample(new SysParamsExample());
        sysParamsMap.clear();
        for (SysParams sysParam:list) {
            sysParamsMap.put(sysParam.getParamKey(), sysParam);
        }
        return list.size();
    }

    public String getParameter(String key) {
        return getParameter(key,"");
    }

    public String getParameter(String key, String default_value) {
        SysParams sysParam = sysParamsMap.get(key);
        if(null == sysParam || null == sysParam.getParamKey() || 0 == sysParam.getParamValue().length())
            return default_value;
        else
            return sysParam.getParamValue();
    }

    public Integer getParameter(String key, Integer default_value) {
        SysParams sysParam = sysParamsMap.get(key);
        if(null == sysParam || null == sysParam.getParamValue() || 0 == sysParam.getParamValue().length())
            return default_value;
        else
            return Integer.valueOf(sysParam.getParamValue());
    }

    public void updateParameter(String key,String value){
        if (!TextUtils.isBlank(value)) {
            SysParams sysParam = new SysParams();
            sysParam.setParamKey(key);
            sysParam.setParamValue(value);
            sysParamsMapper.updateByPrimaryKeySelective(sysParam);
        }
    }

    public void refresh() {
        logger.debug("reload system parameters");
        init();
    }
}
