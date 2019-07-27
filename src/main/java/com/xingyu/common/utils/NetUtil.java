package com.xingyu.common.utils;

import com.xingyu.constants.ParamConsts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class NetUtil {
    private static Log logger = LogFactory.getLog(NetUtil.class);
    private static final String USERAGENT_WEIXIN_FEATURE_STRING = "MicroMessenger";  //微信浏览器特征串
    private static final String USERAGENT_WEIBO_FEATURE_STRING = "Weibo";  //微博浏览器特征串
    private static final String USERAGENT_APLIPAY_FEATURE_STRING = "Alipay";  //支付宝浏览器特征串


    public static String getTopDomain(String host) {
        if(TextUtils.isBlank(host)){
            return ParamConsts.JIHELIFE_COM;
        }else if(host.indexOf(":") > 0 ){
            host = host.substring(0,host.indexOf(":"));
        }
        if(host.contains(ParamConsts.JIHELIFE_COM)){
            return ParamConsts.JIHELIFE_COM;
        }else if(host.contains(ParamConsts.JIHEX_CN)){
            return ParamConsts.JIHEX_CN;
        }else{
            return host;
        }
    }

    public static String getFullUrl(String host,String url){
        if(url.startsWith(ParamConsts.HTTP_PREFIX)  || url.startsWith(ParamConsts.HTTPS_PREFIX)){
            return url;
        }else if(url.startsWith("/")){
            return ParamConsts.HTTP_PREFIX + host + url;
        }else{
            return ParamConsts.HTTP_PREFIX + host + "/" + url;
        }
    }

    /**
     * 判断操作系统
     *
     * @return String
     */
    public static boolean isWindowsOS(){
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if(osName.toLowerCase().contains("windows")){
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     * @return String
     */
    public static String getLocalIP(){
        String sIP = "";
        InetAddress inetAddress = null;
        try {
            //如果是Windows操作系统
            if(isWindowsOS()){
                inetAddress = InetAddress.getLocalHost();
            }
            //如果是Linux操作系统
            else{
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = netInterfaces.nextElement();
                    //----------特定情况，可以考虑用ni.getName判断
                    //遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        inetAddress = ips.nextElement();
                        if( inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().contains(":")){  //127.开头的都是lookback地址
                            bFindIP = true;
                            break;
                        }
                    }
                    if(bFindIP){
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        if(null != inetAddress){
            sIP = inetAddress.getHostAddress();
        }
        return sIP;
    }

}
