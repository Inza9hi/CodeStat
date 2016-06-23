package com.lawulu.bdc.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lawulu on 16-1-19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictInfo {

    private String id;
    private String name;

    public static void main(String[] args) {
        DictInfo dictInfo = new DictInfo();
        dictInfo.setId("11");
    }


}
