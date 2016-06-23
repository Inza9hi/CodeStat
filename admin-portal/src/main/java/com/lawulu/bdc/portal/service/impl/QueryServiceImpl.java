package com.lawulu.bdc.portal.service.impl;

import com.lawulu.bdc.portal.service.QueryService;
import com.lawulu.bdc.portal.vo.DictInfo;
import com.lawulu.bdc.portal.vo.QueryResult;
import com.lawulu.bdc.portal.vo.SqlQueryDto;
import com.lawulu.datacenter.common.db.DbContextHolder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lawulu on 16-1-20.
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    @Qualifier("jdbcTemplate")
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    final String bjDept = "(deptid like '0001%' or deptid like '0099%')";
    final String notBjDept = "(deptid not like '0001%' and deptid not like '0099%')";

    final String sql ="select deptid id,deptname name from as_deptino t where DEPTTYPE ='03' and  ";
    final String bjDeptSql = sql+ bjDept +" order by deptid ";
    final String NotBjDeptSql = sql+ notBjDept +" order by deptid ";

    @Override
    public List<QueryResult> getResult(SqlQueryDto sqlQueryDto) throws SQLException {

        if(!sqlQueryDto.getSql().toUpperCase().startsWith("SELECT")){
            throw new SQLException("Invalid operation");
        }

        int maxSize =sqlQueryDto.getMaxSize()==null?10:sqlQueryDto.getMaxSize();
        if(maxSize>50){
            maxSize=20;//:-D
        }
        List<QueryResult> queryResults = Lists.newArrayList();

        String metaSql = "select * from (" +sqlQueryDto.getSql()+" ) where rownum <="+maxSize;
        Set<String> set = new HashSet<>(sqlQueryDto.getDeptIds());

        for (String deptId : set) {
            DbContextHolder.setDbType(deptId);
            QueryResult query = namedParameterJdbcTemplate.query(metaSql, new QueryResultResultSetExtractor());
            query.setDeptId(deptId);
            DbContextHolder.clearDbType();
            queryResults.add(query);
        }
        return queryResults;

    }



    @Override
    public List<DictInfo> getDeptInfos(String type){
        String sql;
        if("BJ".equals(type)){
            sql=bjDeptSql;
        }else {
            sql=NotBjDeptSql;
        }

        return namedParameterJdbcTemplate.query(sql, Maps.newHashMap(), new BeanPropertyRowMapper(DictInfo.class));

    }

    public static class QueryResultResultSetExtractor  implements ResultSetExtractor<QueryResult>{


        @Override
        public QueryResult extractData(ResultSet rs) throws SQLException, DataAccessException {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<String> columnNames = Lists.newArrayList();
            for(int i = 1 ; i <= columnCount ; i++){
                columnNames.add(rsmd.getColumnName(i));
            }

            QueryResult.QueryResultBuilder queryResultBuilder = QueryResult.builder().columnNames(columnNames);

            List<List<Object>> results = Lists.newArrayList();
            while(rs.next()) {
                List<Object> list = Lists.newArrayList();
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    list.add(JdbcUtils.getResultSetValue(rs,colIndex));
                }
               results.add(list);
            }

            return queryResultBuilder.results(results).build();

        }
    }
}
