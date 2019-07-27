package com.xingyu.base;

/**
 *
 */
public class BizException extends RuntimeException {

    public static final String CODE_SYSTEM_ERROR     = "-9999";       //系统错误
    public static final String MSG_SYSTEM_ERROR     = "悲剧：系统异常了！";

    //基本错误定义
    public static final String CODE_SIGN_ERROR       = "ERROR-1000";       //签名错误
    public static final String MSG_SIGN_ERROR        = "【签名错误】";
    public static final String CODE_IPAUTH_ERROR     = "ERROR-1001";       //IP授权错误
    public static final String MSG_IPAUTH_ERROR      = "【IP未授权】";
    public static final String CODE_PARAM_LACK        = "ERROR-1002";       //缺少必要参数
    public static final String MSG_PARAM_LACK         = "【缺少必要参数】";
    public static final String CODE_PARAM_ERROR       = "ERROR-1003";       //参数错误
    public static final String MSG_PARAM_ERROR        = "【参数错误】";
    public static final String CALL_SERVICE_ERROR     = "ERROR-1004";       //调用服务异常
    public static final String MSG_CALL_SERVICE_ERROR = "调用服务异常";

    
    private String code;
    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
