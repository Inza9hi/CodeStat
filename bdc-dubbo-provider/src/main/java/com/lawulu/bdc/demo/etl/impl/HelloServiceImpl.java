package com.lawulu.bdc.demo.etl.impl;

import com.lawulu.bdc.demo.etl.HelloService;

import java.util.Date;

/**
 * Created by lawulu on 16-3-10.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        if(name.equals("exception")){
            throw new RuntimeException("Exception");
        }
        return "Hello, at "+ new Date() +" " +name;
    }
}
