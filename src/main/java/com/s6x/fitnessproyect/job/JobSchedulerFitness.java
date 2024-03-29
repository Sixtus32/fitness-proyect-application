package com.s6x.fitnessproyect.job;


import java.util.Date;

import org.springframework.batch.core.Job;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.converter.JobParametersConversionException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobSchedulerFitness {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job jobFitness;
    
    @Scheduled(cron = "${miapp.task.scheduling.cron}") // Ejecuta el método cada 5 segundos después de que la ejecución anterior haya finalizado
    public void scheduleJob() throws JobParametersConversionException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        jobLauncher.run(jobFitness, new JobParametersBuilder().addDate("timestamp", new Date()).toJobParameters());
    }
    
}
