package com.lawulu.bdc.etl.batch.scheduled;

import com.lawulu.bdc.etl.batch.model.JobMetaData;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lawulu on 16-3-24.
 */

@DisallowConcurrentExecution // because we store job state between executions
@PersistJobDataAfterExecution // because we store last fire time between executions
public class ReportScheduledJob extends AbstractScheduledJob {


    //根据MetaData表处理run或者reRun,此处逻辑可以写在AbstractScheduledJob
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        JobRegistry jobRegistry = getJobRegistry();
        JobLauncher jobLauncher = getJobLauncher();


        JobMetaData jobMetaData = new JobMetaData();
        jobMetaData.setDept("100");//是否也可以根据barcode?


        Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put("dept", new JobParameter(jobMetaData.getDept()));
        parameters.put("Start", new JobParameter(new Date()));

        try
        {
            JobExecution calculateEventMetricsJob = jobLauncher.run(jobRegistry.getJob("helloJob"), new JobParameters(parameters));

        } catch (Exception e)
        {
            throw new JobExecutionException(e);
        }

    }
}
