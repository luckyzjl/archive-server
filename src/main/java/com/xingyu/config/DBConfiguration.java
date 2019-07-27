package com.xingyu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.xingyu.mapper")
public class DBConfiguration {

}
