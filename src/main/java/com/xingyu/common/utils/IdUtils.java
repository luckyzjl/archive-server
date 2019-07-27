package com.xingyu.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.UUID;


public class IdUtils {
    /**
     * 生成唯一ID,只适用于当前并发量不高的情况，并发量高后，此方法不适用
     * @return
     */
    public static String generateId(){
        int hashCode = UUID.randomUUID().toString().hashCode();
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        return DateFormatUtils.format(System.currentTimeMillis(),"yyMMdd") + String.format("%011d", hashCode);
    }

    /**
     * 生成唯一ID
     * @return
     */
    public static String generateSimpleId(){
        int hashCode = UUID.randomUUID().toString().hashCode();
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        return String.format("%d", hashCode);
    }
}
