package com.lawulu.bdc.portal.service;

import com.lawulu.bdc.portal.AbstractApplicationContext;
import com.lawulu.bdc.portal.vo.SqlQueryDto;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

/**
 * Created by lawulu on 16-1-20.
 */
public class QueryServiceTest extends AbstractApplicationContext {

    @Autowired
    QueryService queryService;

    @Test
    public void test() throws SQLException {
        SqlQueryDto sqlQueryDto = SqlQueryDto.builder().sql("select * from T_SINGLE_CHECKINFO")
                .maxSize(20).deptIds(Lists.newArrayList()).build();
        queryService.getResult(sqlQueryDto);

    }

}
