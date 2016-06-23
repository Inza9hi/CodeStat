package com.lawulu.bdc.portal.service;

import com.lawulu.bdc.portal.vo.DictInfo;
import com.lawulu.bdc.portal.vo.QueryResult;
import com.lawulu.bdc.portal.vo.SqlQueryDto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lawulu on 16-1-20.
 */
public interface QueryService {

    List<QueryResult> getResult(SqlQueryDto sqlQueryDto) throws SQLException;

    List<DictInfo> getDeptInfos(String type);
}
