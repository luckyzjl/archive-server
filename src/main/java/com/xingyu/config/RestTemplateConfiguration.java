package com.xingyu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Configuration
public class RestTemplateConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfiguration.class);

    @Autowired
    private ClientHttpRequestFactory clientHttpRequestFactory;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.getInterceptors().add(new RestTemplateInterceptor());
        return restTemplate;
    }

    class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
        /**
         * 记录所有使用RestTemplate的请求日志和耗时
         */
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            long start = System.currentTimeMillis();
            try {
                ClientHttpResponse response = execution.execute(request, body);
                logger.debug("{} {},timeConsume={}ms,responseStatus={},requestBody={}",request.getMethod(),request.getURI(),(System.currentTimeMillis() - start), response.getStatusCode(), new String(body,"UTF-8"));
                return response;
            }catch (Exception e){
                logger.debug("{} {},timeConsume={}ms,responseStatus={},requestBody={}",request.getMethod(),request.getURI(),(System.currentTimeMillis() - start), e.getMessage(), new String(body,"UTF-8"));
                throw e;
            }
        }
    }
}
