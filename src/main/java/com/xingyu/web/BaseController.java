package com.xingyu.web;


import com.xingyu.base.BizException;
import com.xingyu.constants.ParamConsts;
import com.xingyu.wrapper.ApiResponse;
import com.xingyu.wrapper.WrapResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
public class BaseController {
    private static Log logger = LogFactory.getLog(BaseController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public ApiResponse<?> exceptionHandler(Throwable e) {
        logger.error(e,e);
        return WrapResponse.wrap("-9999","系统异常^_^");
    }


    /**
     * 拦截捕捉自定义异常 BizException.class
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ApiResponse<?> exceptionHandler(NullPointerException e) {
        logger.error(e,e);
        Map map = new HashMap();
        map.put(ParamConsts.TAG_SC, -9999);
        map.put(ParamConsts.TAG_SC_MSG,"系统异常(空指针)");
        return WrapResponse.wrap("-9999","系统异常(空指针)");
    }

    /**
     * 拦截参数不合格异常 IllegalArgumentException.class
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ApiResponse<?> illegalArgumentHandler(IllegalArgumentException e) {
        logger.error(e,e);
        return WrapResponse.wrap("IllegalArgument",e.getMessage());
    }


    /**
     * 拦截捕捉自定义异常 BizException.class
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ApiResponse<?> exceptionHandler(BizException e) {
        if("-1".equals(e.getCode())) {
            logger.error(e.getMessage());
        }else{
            logger.error(e.getMessage(),e);
        }
        return WrapResponse.wrap(Optional.ofNullable(e.getCode()).orElse("-9999"),e.getMessage());
    }

}
