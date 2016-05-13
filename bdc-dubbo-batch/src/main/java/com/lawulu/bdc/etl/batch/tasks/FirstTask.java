package com.lawulu.bdc.etl.batch.tasks;

import com.lawulu.bdc.demo.etl.ReportSpiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lawulu on 16-3-23.
 */
@Component("firstTask")
public class FirstTask implements Tasklet {

    protected static final Logger logger = LoggerFactory.getLogger(FirstTask.class);

    @Autowired
    @Qualifier("reportSpiderService")
    ReportSpiderService reportSpiderService;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        logger.info("In the FirstTask");
//        StepContext stepContext = chunkContext.getStepContext();
////        stepContext.setAttribute("step", "liulu");
//        stepContext.getJobExecutionContext().put("job", "job");
//@see http://stackoverflow.com/questions/8117060/storing-in-jobexecutioncontext-from-tasklet-and-accessing-in-another-tasklet

        List<String> list =reportSpiderService.crawlReports(null,null,null);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("value", "foo");

        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("job", list);

        return RepeatStatus.FINISHED;
    }
}
