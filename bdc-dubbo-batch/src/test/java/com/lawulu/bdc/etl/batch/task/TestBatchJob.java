package com.lawulu.bdc.etl.batch.task;

import com.lawulu.bdc.etl.batch.test.AbstractApplicationContext;
import org.junit.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lawulu on 16-3-23.
 */
public class TestBatchJob extends AbstractApplicationContext {

    @Autowired
    JobRegistry jobRegistry;
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobExplorer jobExplorer;


    @Test
    public void demo(){
        Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put("startingFrom", new JobParameter("Dubbo-Test12"));

        try
        {
            JobExecution jobExecution = jobExplorer.getJobExecution(14L);
//            if(jobExecution!=null){
//             jobOperator.restart(31L);
//            }else {
                jobLauncher.run(jobRegistry.getJob("helloJob"), new JobParameters(parameters));

//            }

        } catch (Exception e)
        {
          e.printStackTrace();
        }

    }
}
