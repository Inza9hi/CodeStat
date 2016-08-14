package com.lawulu.bdc.etl.batch.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * Created by lawulu on 16-3-9.
 */
public class ContextTest extends AbstractApplicationContext {

    @Autowired
    DataSource dataSource;
//    ArticleFlow articleFlow;

//    @Value("#{config['batch.db.type']}")
//    private String test;

    @Test
    public void test(){
        dataSource.hashCode();
//        System.out.println(test);
//        articleFlow.process(null);

    }

}
