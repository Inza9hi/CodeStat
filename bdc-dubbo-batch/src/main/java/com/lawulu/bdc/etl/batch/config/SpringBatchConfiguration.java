package com.lawulu.bdc.etl.batch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource({"classpath:spring-batch-conf.xml","classpath:spring-batch-tasks.xml"})
@ComponentScan("com.lawulu.bdc.etl.batch.tasks,com.lawulu.bdc.etl.batch.scheduler")
public class SpringBatchConfiguration{

}
