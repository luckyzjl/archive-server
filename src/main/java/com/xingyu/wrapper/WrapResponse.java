package com.xingyu.wrapper;

import java.io.Serializable;

/**
 * Created by maobg on 2018/11/22.
 */
public class WrapResponse implements Serializable{
    private  final static String SC_SUCC = "0";

    private WrapResponse() {
    }

    /**
     * 简单成功响应
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> wrapSuccess() {
        return wrap(SC_SUCC, null,null,null);
    }

    /**
     * 异常响应
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> wrap(String code, String message) {
        return wrap(code, message, null,null);
    }

    /**
     * 异常响应
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> wrap(String code, String message, T data) {
        return wrap(code, message, data,null);
    }

    /**
     * 单个对象的成功响应
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> wrap(T data) {
        return wrap(SC_SUCC, null, data,null);
    }

    /**
     * 列表的成功响应
     * @param data
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> wrap(T data, ApiResponse.PageInfo pageInfo) {
        return wrap(SC_SUCC, null, data,pageInfo);
    }


    /**
     * 最完整的响应构造
     * @param code
     * @param message
     * @param data
     * @param pageInfo
     * @param <T>
     * @return
     */
    private static <T> ApiResponse<T> wrap(String code, String message, T data, ApiResponse.PageInfo pageInfo) {
        return new ApiResponse(code, message, data, pageInfo);
    }

    /**
     * 从响应中解码出对象
     * @param wrapper
     * @param <T>
     * @return
     */
    public static <T> T unWrap(ApiResponse<T> wrapper) {
        return wrapper.getData();
    }

}
