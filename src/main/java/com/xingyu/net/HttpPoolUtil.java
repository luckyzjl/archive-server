package com.xingyu.net;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import com.google.gson.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xingyu.base.BizException;

@Scope("singleton")
@Component
public class HttpPoolUtil {
    private static Log logger = LogFactory.getLog(HttpPoolUtil.class);

    private static final int REQUEST_TIMEOUT = 5000;  //从连接池中获取连接的超时时间
    private static final int CONNECT_TIMEOUT = 5000;  //连接server的超时时间
    private static final int SOCKET_TIMEOUT  = 30000;  //socket读数据超时时间
    private static final int SO_TIMEOUT = 5000;

    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private CloseableHttpClient closeableHttpClient;
    private FutureRequestExecutionService futureRequestExecutionService;

    public HttpPoolUtil() {
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(SO_TIMEOUT).setSoKeepAlive(true).setTcpNoDelay(true).build());
        poolingHttpClientConnectionManager.setDefaultConnectionConfig(ConnectionConfig.custom().setCharset(Consts.UTF_8).build());
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);

        closeableHttpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setConnectionRequestTimeout(REQUEST_TIMEOUT).build())
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .build();

        futureRequestExecutionService = new FutureRequestExecutionService(closeableHttpClient,Executors.newFixedThreadPool(200));
    }

    public  <T> T doRequest(HttpRequestBase request,Class<T> clazz) throws BizException{
        return doRequest(request,clazz,true);
    }

    public  <T> T doRequest(HttpRequestBase request,Class<T> clazz,boolean logresponse) throws BizException{

        HttpResponse httpResponse = null;
        String stringResponse = null;
        T jsonResponse = null;
        long start = System.currentTimeMillis();
        try {
            try {
                httpResponse = getCloseableHttpClient().execute(request);
            } catch (IOException e) {
                logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=" + e.getMessage(), e);
                throw new BizException(BizException.CALL_SERVICE_ERROR, e.getMessage());
            }
            if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
                logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=" + httpResponse.getStatusLine().getReasonPhrase());
                throw new BizException(BizException.CALL_SERVICE_ERROR, httpResponse.getStatusLine().getReasonPhrase());
            }
            try {
                HttpEntity httpEntity = httpResponse.getEntity();
                stringResponse = EntityUtils.toString(httpEntity, Consts.UTF_8);
            } catch (IOException e) {
                logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=" + e.getMessage(), e);
                throw new BizException(BizException.CALL_SERVICE_ERROR, e.getMessage());
            }

            if (null == stringResponse) {
                logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=read response error");
                throw new BizException(BizException.CALL_SERVICE_ERROR, "read response error");
            }
        } finally {
            try {
                request.releaseConnection();
            } catch (Exception e) {
                logger.error(e, e);
            }
        }
        try {
            jsonResponse = JSON.parseObject(stringResponse, clazz);
        } catch (Exception e1) {
            logger.error(e1,e1);
            try {
                jsonResponse = new Gson().fromJson(stringResponse, clazz);
            } catch (Exception e2) {
                logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response=" + stringResponse + " |exception=" + e2.getMessage(), e2);
                throw new BizException(BizException.CALL_SERVICE_ERROR, e2.getMessage());
            }
        }

        if (!(jsonResponse instanceof BaseMethod.JiheResponse)) {
            logger.info("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms  |response=" + stringResponse);
            throw new BizException(BizException.CALL_SERVICE_ERROR, "不支持的Response类型");
        }

        BaseMethod.JiheResponse superResponse = (BaseMethod.JiheResponse) jsonResponse;
        if ((null !=superResponse.getErrcode() && 0 != superResponse.getErrcode())  //微信
                || (null != superResponse.getSc() && !"0".equals(superResponse.getSc()))   //几何
                ) {
            String errorMessage  = null != superResponse.getErrmsg() ? superResponse.getErrmsg() : null != superResponse.getErrorMsg() ? superResponse.getErrorMsg() : "调用外部服务异常";
            String errorCode = !TextUtils.isBlank(superResponse.getSc()) ? superResponse.getSc() : BizException.CALL_SERVICE_ERROR;
            logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms  |response=" + stringResponse + " |exception=" + errorMessage);
            throw new BizException(errorCode, errorMessage);
        }
        logger.info("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms  |response=" + (true == logresponse ? stringResponse : ""));
        return jsonResponse;
    }

    public <T> void doAsyncRequest(final HttpRequestBase request,final Class<T> clazz){
        doAsyncRequest(request,clazz,null,true);
    }

    public <T> void doAsyncRequest(final HttpRequestBase request,final Class<T> clazz,final CustomResponseHandler<T> responseHandler){
        doAsyncRequest(request,clazz,responseHandler,true);
    }

    public <T> void doAsyncRequest(final HttpRequestBase request,final Class<T> clazz,final CustomResponseHandler<T> responseHandler,final boolean logresponse){
        final long start = System.currentTimeMillis();
        futureRequestExecutionService.execute(request, null, new ResponseHandler<T>() {
            @Override
            public T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                String stringResponse = null;
                T jsonResponse = null;
                if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
                    logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=" + httpResponse.getStatusLine().getReasonPhrase());
                    //throw new BizException(BizException.CALL_SERVICE_ERROR, httpResponse.getStatusLine().getReasonPhrase());
                    return null;
                }
                try {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    stringResponse = EntityUtils.toString(httpEntity, Consts.UTF_8);
                } catch (IOException e) {
                    logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=" + e.getMessage(), e);
                    //throw new BizException(BizException.CALL_SERVICE_ERROR, e.getMessage());
                    return null;
                }

                if (TextUtils.isBlank(stringResponse)) {
                    logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response= |exception=read response error");
                    //throw new BizException(BizException.CALL_SERVICE_ERROR, "read response error");
                    return null;
                }
                try {
                    jsonResponse = JSON.parseObject(stringResponse, clazz);
                } catch (Exception e1) {
                    logger.error(e1,e1);
                    try {
                         Gson dateGson = new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                                    @Override
                                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        return new Date(json.getAsJsonPrimitive().getAsLong());
                                    }
                                }).create();
                        jsonResponse = dateGson.fromJson(stringResponse, clazz);
                    } catch (Exception e2) {
                        logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms |response=" + stringResponse + " |exception=" + e2.getMessage(), e2);
                        //throw new BizException(BizException.CALL_SERVICE_ERROR, e2.getMessage());
                        return null;
                    }
                }

                if (!(jsonResponse instanceof BaseMethod.JiheResponse)) {
                    logger.info("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms  |response=" + stringResponse);
                    //throw new BizException(BizException.CALL_SERVICE_ERROR, "不支持的Response类型");
                    return null;
                }

                BaseMethod.JiheResponse superResponse = (BaseMethod.JiheResponse) jsonResponse;
                if (superResponse.getErrcode() != 0 || !"0".equals(superResponse.getSc())) {
                    String errorMessage = null == superResponse.getErrmsg() ? superResponse.getErrorMsg() : superResponse.getErrmsg();

                    logger.error("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms  |response=" + stringResponse + " |exception=" + errorMessage);
                    //throw new BizException(BizException.CALL_SERVICE_ERROR, superResponse.getErrmsg());
                    return null;
                }
                logger.info("request=" + request + "|timeconsume=" + (System.currentTimeMillis() - start) + "ms  |response=" + (true == logresponse ? stringResponse : ""));
                if (null != responseHandler) {
                    responseHandler.handleResponse(jsonResponse);
                }
                return jsonResponse;
            }
        });
    }

    @PreDestroy
    public void close() {
        logger.debug("poolingHttpClientConnectionManager close");
        if (this.getPoolingHttpClientConnectionManager() != null) {
            this.getPoolingHttpClientConnectionManager().close();
        }
    }

    @Scheduled(fixedDelay = 5000)   //5秒清理一次
    public void idle_con_release() {
        if (this.getPoolingHttpClientConnectionManager() != null) {
            this.getPoolingHttpClientConnectionManager().closeExpiredConnections();
            this.getPoolingHttpClientConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
        }
    }

    public PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        return this.poolingHttpClientConnectionManager;
    }

    private CloseableHttpClient getCloseableHttpClient() {
        return this.closeableHttpClient;
    }
}
