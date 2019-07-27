package com.xingyu.config;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClientConfiguration {
    private static final int REQUEST_TIMEOUT = 3000;  //从连接池中获取连接的超时时间
    private static final int CONNECT_TIMEOUT = 10000;  //连接server的超时时间
    private static final int SOCKET_TIMEOUT  = 10000; //socket读数据超时时间

    private static final int MAX_CONNECTION  = 1000;  //连接池最大连接数
    private static final int MAX_PERROUTE    = 100;   //每个主机的最大连接数

    public static final int TIMER_REPEAT = 3000;  //清理超时连接的周期

    private CloseableHttpClient httpClient;
    private PoolingHttpClientConnectionManager httpClientConnectionManager;

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }

    @Bean
    public CloseableHttpClient httpClient(HttpClientBuilder httpClientBuilder){
        httpClient = httpClientBuilder.build();
        return httpClient;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager httpClientConnectionManager) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //设置HTTP连接管理器
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        return httpClientBuilder;
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        if(null == httpClientConnectionManager) {
            httpClientConnectionManager = new PoolingHttpClientConnectionManager();
            httpClientConnectionManager.setDefaultSocketConfig(SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).build());
            httpClientConnectionManager.setDefaultConnectionConfig(ConnectionConfig.custom().setCharset(Consts.UTF_8).build());
            httpClientConnectionManager.setMaxTotal(MAX_CONNECTION); //连接池最大连接数
            httpClientConnectionManager.setDefaultMaxPerRoute(MAX_PERROUTE); // 每个主机的并发
        }
        return httpClientConnectionManager;
    }

    //3秒清理一次
    @Scheduled(initialDelay = 30000, fixedDelay = TIMER_REPEAT)
    public void closeExpiredConnections() {
        if(null != httpClientConnectionManager) {
            httpClientConnectionManager.closeExpiredConnections();
            httpClientConnectionManager.closeIdleConnections(10,TimeUnit.SECONDS);
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        if(httpClient != null) {
            httpClient.close();
        }
        if(null != httpClientConnectionManager) {
            httpClientConnectionManager.close();
        }
    }

}
