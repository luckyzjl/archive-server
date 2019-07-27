package com.xingyu.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        //这里记录所有，根据实际情况选择合适的日志level
        return Logger.Level.FULL;
    }

    /**
     * 使FeignClient框架-参数支持postbody中提交，而不是拼接在url中
     */
    @Bean
    public Encoder feignEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder((new SpringEncoder(messageConverters)));
    }

    /**
     * 实际发现在WebMvcConfigurer.extendMessageConverters中添加的消息转换器对FeignClient调用没有效果，重定义messageConverters Bean替换FeignClient使用的messageConverters
     */
    @Bean
    public HttpMessageConverters messageConverters(){
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();

        //序列化  Object --> Json
        //null值不序列化
        objectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        //数字也加引号
        //builder.featuresToEnable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        //builder.featuresToEnable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);

        //反序列化 Json --> Object
        //属性不存在，不抛出异常
        objectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //大小写脱敏
        objectMapperBuilder.featuresToDisable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);

        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter(objectMapperBuilder.build()));
    }
}
