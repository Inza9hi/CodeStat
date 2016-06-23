package com.lawulu.bdc.portal.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by lawulu on 16-1-15.
 */
@Controller
public class HolderController {

    @RequestMapping("/hello")
    public String index(){
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){

        Map<String,Set<String>> map = Maps.newHashMap();
        map.computeIfAbsent("ste", k -> new HashSet<String>()).add("sdf");

        return new Date();
    }
}
