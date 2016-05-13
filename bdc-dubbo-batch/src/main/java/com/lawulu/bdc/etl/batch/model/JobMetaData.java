package com.lawulu.bdc.etl.batch.model;

/**
 * Created by lawulu on 16-3-24.
 */
public class JobMetaData {

    //是否也可以根据barcode?
    //需要加上策略限制，例如Guava的LimitRate，防止对某一分院太多查询

    private String jobType;

    private String dept;

    private String startTime;

    private String endTime;

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
