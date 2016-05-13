package com.lawulu.bdc.demo.dubbo;


import com.lawulu.bdc.demo.etl.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by lawulu on 16-3-10.
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"file:/home/lawulu/dev/svn/codes/poc/dubbo-demo/bdc-dubbo-consumer/src/main/resources/META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();

        HelloService demoService = (HelloService)context.getBean("demoService"); // 获取远程服务代理

        while(true){
            TimeUnit.SECONDS.sleep(5);
            String hello = demoService.hello("exception"); // 执行远程方法
            System.out.println(Thread.currentThread()+ ":--"+ hello); // 显示调用结果
        }


    }
}
