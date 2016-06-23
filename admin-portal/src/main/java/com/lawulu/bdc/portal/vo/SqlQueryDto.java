package com.lawulu.bdc.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lawulu on 16-1-21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SqlQueryDto {

    private String sql;
    private List<String> deptIds;
    private Integer maxSize;
}
