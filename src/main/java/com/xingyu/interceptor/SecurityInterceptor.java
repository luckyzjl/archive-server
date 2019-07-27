package com.xingyu.interceptor;

import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.web.RequestFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Created by zhujl on 2016/3/6.
 */
public class SecurityInterceptor  implements HandlerInterceptor {

    private static Log logger = LogFactory.getLog(SecurityInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        ReqParameter reqParameter = RequestFacade.getParameters();

        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
