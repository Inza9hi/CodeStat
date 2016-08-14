package com.lawulu.bdc.portal;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml"

//		"file:src/test/resources/config/config-local.properties",
		})
public abstract class AbstractApplicationContext {
	@Autowired 
	protected ApplicationContext context;
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractApplicationContext.class);


	public AbstractApplicationContext(){
		System.setProperty("envTarget", "local");
	}
}