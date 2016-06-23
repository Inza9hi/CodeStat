package com.lawulu.bdc.portal.controller;

import com.lawulu.bdc.portal.service.QueryService;
import com.lawulu.bdc.portal.vo.DictInfo;
import com.lawulu.bdc.portal.vo.SqlQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lawulu on 16-1-20.
 */
@RestController
public class DeptQueryController {

    @Autowired
    QueryService queryService;

    @RequestMapping(value = "dict/subcoms", method = RequestMethod.GET)
    public List<DictInfo> subcoms(@RequestParam(value = "type", required = true)String type) {
        return queryService.getDeptInfos(type);
    }

    @RequestMapping(value = "dept/sqlQuery", method = RequestMethod.POST)
    public Object sqlQuery(@RequestBody SqlQueryDto sqlQueryDto) throws SQLException {
        return queryService.getResult(sqlQueryDto);

    }

}
