package com.lawulu.bdc.etl.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource({"classpath:META-INF/spring/dubbo-demo-consumer.xml"})
public class DubboConfiguration {

}
