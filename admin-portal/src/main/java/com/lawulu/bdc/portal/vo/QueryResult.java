package com.lawulu.bdc.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lawulu on 16-1-20.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryResult {

    private String deptId;
    private List<String> columnNames;
    private List<List<Object>> results;
}
