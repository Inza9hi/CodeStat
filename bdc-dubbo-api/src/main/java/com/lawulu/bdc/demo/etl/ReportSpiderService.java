package com.lawulu.bdc.demo.etl;

import java.util.List;

/**
 * Created by lawulu on 16-3-24.
 */
public interface ReportSpiderService {
    List<String> crawlReports(String dept,String startTime,String endTime);

    void generateReport(String barcode);
}
