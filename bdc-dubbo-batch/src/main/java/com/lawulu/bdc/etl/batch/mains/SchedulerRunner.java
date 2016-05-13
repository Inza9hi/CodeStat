/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lawulu.bdc.etl.batch.mains;

import com.lawulu.bdc.etl.batch.config.DatabaseConfiguration;
import com.lawulu.bdc.etl.batch.config.DubboConfiguration;
import com.lawulu.bdc.etl.batch.config.SpringBatchConfiguration;
import com.lawulu.bdc.etl.batch.scheduled.ReportScheduledJob;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchedulerRunner
{
    private final ApplicationContext appCtx;
    private final Scheduler scheduler;

    public SchedulerRunner()
    {
        super();
        appCtx = new AnnotationConfigApplicationContext(new Class[]{DatabaseConfiguration.class, DubboConfiguration.class, SpringBatchConfiguration.class});
        scheduler = appCtx.getBean(Scheduler.class);
    }

    public static void main(String[] args) throws SchedulerException
    {
        SchedulerRunner schedulerRunner = new SchedulerRunner();
        schedulerRunner.invoke();
    }

    public void invoke() throws SchedulerException
    {
       scheduleReportEvent();

//        scheduleLongRunningBatch();
        scheduler.start();
    }

    private void scheduleReportEvent() throws SchedulerException
    {
        JobDetail job = newJob(ReportScheduledJob.class)
                .withIdentity("ReportScheduledJob", "ReportJob")
                .requestRecovery(false)
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity("triggerFor_ReportScheduledJob", "ReportJob")
                .withSchedule(cronSchedule("0 0/1 * * * ?").inTimeZone(TimeZone.getTimeZone("UTC"))
                        .withMisfireHandlingInstructionFireAndProceed())
                .forJob(job.getKey())
                .build();
        trigger.getJobDataMap().putAsString("finishedAt", System.currentTimeMillis());
        scheduleJobWithTriggerIfNotPresent(job, trigger);
    }



    private void scheduleJobWithTriggerIfNotPresent(JobDetail job, Trigger trigger) throws SchedulerException
    {
        if (!scheduler.checkExists(job.getKey()) && !scheduler.checkExists(trigger.getKey()))
        {
            try
            {
                scheduler.scheduleJob(job, trigger);
            } catch (ObjectAlreadyExistsException existsExc)
            {
                System.out.println("Someone has already scheduled such job/trigger. " + job.getKey() + " : " + trigger.getKey());
            }
        }
    }
}
