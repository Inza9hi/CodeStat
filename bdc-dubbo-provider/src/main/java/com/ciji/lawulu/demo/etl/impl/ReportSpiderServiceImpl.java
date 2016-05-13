package com.ciji.lawulu.demo.etl.impl;

import com.lawulu.bdc.demo.etl.ReportSpiderService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lawulu on 16-3-24.
 */
public class ReportSpiderServiceImpl implements ReportSpiderService {

    @Override
    public List<String> crawlReports(String dept, String startTime, String endTime) {

        return  Arrays.asList(new String[]{"1","2"});
    }

    @Override
    public void generateReport(String barcode) {

    }
}
