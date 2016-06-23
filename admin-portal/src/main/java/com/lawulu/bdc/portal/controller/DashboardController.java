/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.lawulu.bdc.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class DashboardController {
    

    
    @RequestMapping(method = RequestMethod.GET)
    public String homepage() {

        return "redirect:subcom_query";
    }

    public String personStats(){
        return "stats";

    }

    @RequestMapping(value = "overview", method = RequestMethod.GET)
    public String overview(final ModelMap model) {
        model.put("activedTab", 0);
        return "overview";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "subcom_query", method = RequestMethod.GET)
    public String subcomQuery() {
        return "subcom_query";
    }




//    @RequestMapping(value = "help", method = RequestMethod.GET)
//    public String help(final ModelMap model) {
//        model.put("activedTab", 2);
//        return "help";
//    }
}
