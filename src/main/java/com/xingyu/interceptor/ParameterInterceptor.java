package com.xingyu.interceptor;

import com.alibaba.fastjson.JSON;
import com.xingyu.constants.ParamConsts;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.web.RequestFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class ParameterInterceptor implements HandlerInterceptor {

    private static Log logger = LogFactory.getLog(ParameterInterceptor.class);

    private final static DefaultConversionService defaultConversionService = new DefaultConversionService();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("***********************************************");
        logger.info("request=" + String.format("%s %s",request.getMethod(),request.getServletPath()));
        Enumeration<String> header =   request.getHeaderNames();
        while (header.hasMoreElements()){
            String headername = header.nextElement();
            logger.info(headername + ":" + request.getHeader(headername));
        }
        Map<String,Object> paramMap = new HashMap<>();
        for(String param:request.getParameterMap().keySet()){
            String[] paramValues = request.getParameterValues(param);
            Object paramValue = (null != paramValues && paramValues.length == 1 ? paramValues[0] : paramValues);
            Field field = ReqParameter.getFieldMap().get(param.toLowerCase().replace("_",""));
            if(null != field){
                try {paramMap.put(param, defaultConversionService.convert(paramValue, field.getType())); }catch (Exception e){
                    logger.error(String.format("Conversion error param=%s,values=%s to field=%s",param, StringUtils.join(paramValues,","), field.getName()));
                }
            }else{
                paramMap.put(param,paramValue);
            }
        }

        Map<String,String> cookieMap = new HashMap<>();
        if(null != request.getCookies()){
            Cookie[]  cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(!TextUtils.isBlank(cookie.getValue())) {
                    cookieMap.put(cookie.getName(), cookie.getValue());
                }
            }
        }

        RequestFacade.setParameters(null);
        ReqParameter parameterInfo = new ReqParameter();

        if(!TextUtils.isBlank(request.getParameter(ParamConsts.TAG_DATA))) {
            String dataJson = request.getParameter(ParamConsts.TAG_DATA);
            logger.info("data:" + dataJson);

            //参数包裹在DATA中传递
            parameterInfo = JSON.parseObject(dataJson, ReqParameter.class);
            parameterInfo.setDataJson(dataJson);
        }else if (!TextUtils.isBlank(request.getContentType()) && request.getContentType().contains(ParamConsts.APPLICATION_JSON)){
            //参数以POST BODY JSON传递
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = request.getReader();
            String string;
            while ((string = reader.readLine()) != null) {
                buffer.append(string);
            }
            reader.close();
            String bodyJson = buffer.toString();
            logger.info("body:" + bodyJson);

            if(!TextUtils.isBlank(bodyJson)) {
                parameterInfo = JSON.parseObject(bodyJson, ReqParameter.class);
                parameterInfo.setBodyJson(bodyJson);
            }
        }else{
            //参数以独立参数传递
            String paramJson = JSON.toJSONString(paramMap);
            logger.info("param:" + paramJson);

            parameterInfo = JSON.parseObject(paramJson,ReqParameter.class);
            parameterInfo.setParamJson(paramJson);
        }
        logger.info("***********************************************");

        parameterInfo.setClientrealip(this.getClientRealIp(request));
        parameterInfo.setAction(request.getServletPath());
        parameterInfo.setQueryString(request.getQueryString());
        parameterInfo.setUseragent(request.getHeader(ParamConsts.HEADER_AGENT));
        parameterInfo.setLocation(request.getHeader(ParamConsts.HEADER_LOCATION));
        parameterInfo.setHost(request.getHeader(ParamConsts.HEADER_HOST));

        if(TextUtils.isBlank(parameterInfo.getLoginToken())) {
            parameterInfo.setLoginToken(request.getHeader(ParamConsts.PARAM_NAME_LOGIN_TOKEN));
            if(TextUtils.isBlank(parameterInfo.getLoginToken())){
                parameterInfo.setLoginToken(cookieMap.get(ParamConsts.PARAM_NAME_LOGIN_TOKEN));
            }
        }

        RequestFacade.setParameters(parameterInfo);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 获取真实的客户端IP地址
     * @param request
     * @return
     */
    private String getClientRealIp(HttpServletRequest request){
        String remoteip = request.getHeader(ParamConsts.HEADER_REMOTEIP);  //经过阿里负载均衡
        if(!TextUtils.isBlank(remoteip)){
            return remoteip;
        }
        String x_real_ip = request.getHeader(ParamConsts.HEADER_X_REAL_IP);  //经过NGINX
        if(!TextUtils.isBlank(x_real_ip)){
            return x_real_ip;
        }
        String x_forwarded_for = request.getHeader(ParamConsts.HEADER_X_FORWARDED_FOR);   //经过多层网关
        if(!TextUtils.isBlank(x_forwarded_for)){
            return x_forwarded_for.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
