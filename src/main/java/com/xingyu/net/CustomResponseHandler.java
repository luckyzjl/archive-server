package com.xingyu.net;


public interface CustomResponseHandler<T> {
    public void handleResponse(T response);
}
