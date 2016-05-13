package com.ciji.lawulu.etl.batch.test;

import com.lawulu.bdc.etl.batch.config.DatabaseConfiguration;
import com.lawulu.bdc.etl.batch.config.DubboConfiguration;
import com.lawulu.bdc.etl.batch.config.SpringBatchConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by lawulu on 16-3-23.
 */
@Configuration
@Import({DatabaseConfiguration.class,SpringBatchConfiguration.class, DubboConfiguration.class})
public class TestConfiguration {
}
