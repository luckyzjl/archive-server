package com.xingyu.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.xingyu.interceptor.LogInterceptor;
import com.xingyu.interceptor.ParameterInterceptor;
import com.xingyu.interceptor.SecurityInterceptor;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.util.TextUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //支持url大小写不敏感
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    /**
     * 定制json序列化的配置，null值属性不序列化
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iter = converters.iterator();
        while (iter.hasNext()){
            HttpMessageConverter<?> converter = iter.next();
            if(converter instanceof MappingJackson2HttpMessageConverter){
                iter.remove();
            }
        }
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();

        //序列化  Object --> Json
        //null值不包含不序列化
        objectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        //数字也加引号
        objectMapperBuilder.featuresToEnable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        objectMapperBuilder.featuresToEnable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);

        //反序列化 Json --> Object
        //属性不存在，不抛出异常
        objectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //大小写脱敏
        objectMapperBuilder.featuresToDisable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);

        converters.add(new MappingJackson2HttpMessageConverter(objectMapperBuilder.build()));
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(parameterInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(securityInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LogInterceptor logInterceptor(){
        return new LogInterceptor();
    }

    @Bean
    public SecurityInterceptor securityInterceptor(){
        return new SecurityInterceptor();
    }

    @Bean
    public ParameterInterceptor parameterInterceptor(){
        return new ParameterInterceptor();
    }



    /**
     * 处理日期类型的转换，支持时间戳
     */
    public static class DateConverter implements Converter<String,Date> {
        private static final String[] DEFFAULT_DATE_FORMAT = new String[] {
                "yyyy-MM-dd",
                "yyyy-MM-dd hh:mm:ss",
                "yyyy/MM/dd",
                "yyyy/MM/dd hh:mm:ss"
        };

        @Override
        public Date convert(String source) {
            if(TextUtils.isBlank(source)){
                return null;
            }
            try {
                if (source.indexOf('-') > -1 || source.indexOf('/') > 0) {
                    return DateUtils.parseDate(source, DEFFAULT_DATE_FORMAT);
                } else if(source.length() <= 10 ){ //UNINX时间戳
                    return new Date(Long.valueOf(source) * 1000);
                } else {
                    return new Date(Long.valueOf(source));
                }
            }catch (Exception e){
                throw new IllegalArgumentException("Illegal date value '"+source+"'");
            }
        }
    }
}
