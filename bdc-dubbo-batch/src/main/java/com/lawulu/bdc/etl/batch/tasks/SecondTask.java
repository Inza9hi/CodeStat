package com.lawulu.bdc.etl.batch.tasks;

import com.lawulu.bdc.demo.etl.HelloService;
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
@Component("secondTask")
public class SecondTask implements Tasklet {

    protected static final Logger logger = LoggerFactory.getLogger(SecondTask.class);

    @Autowired
    @Qualifier("demoService")
    HelloService demoService ;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        logger.info("In the Sencond Task");


        String value = (String) chunkContext.getStepContext().getJobExecutionContext().get("value");

        logger.info(value);

        List<String> job = (List) chunkContext.getStepContext().getJobExecutionContext().get("job");

        for (String s : job) {
            String hello = demoService.hello(s); // 执行远程方法
            System.out.println(Thread.currentThread() + ":--" + hello); // 显示调用结果
        }

        //此处可使用ReaderProcessWriter的方法处理整个
        return RepeatStatus.FINISHED;
    }
}
