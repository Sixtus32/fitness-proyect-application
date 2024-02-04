package com.s6x.fitnessproyect.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.s6x.fitnessproyect.job.InsertIntoAccountTasklet;
import com.s6x.fitnessproyect.job.ProcessToTrueTasklet;
import com.s6x.fitnessproyect.job.UnprocessedSubscriptinsTasklet;

@Configuration
public class BatchConfiguration {
    @Bean
    protected Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
          .tasklet(unprocessedSubTasklet(), transactionManager)
          .build();
    }
    
    @Bean
    protected Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
          .tasklet(insertIntoAccountTasklet(), transactionManager)
          .build();
    }
    
    @Bean
    protected Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step3", jobRepository)
          .tasklet(processToTrueTasklet(), transactionManager)
          .build();
    }
    
    @Bean
    protected UnprocessedSubscriptinsTasklet unprocessedSubTasklet() {
	    	UnprocessedSubscriptinsTasklet tasklet = new UnprocessedSubscriptinsTasklet();
	    	return tasklet;
    }
    
    @Bean
    protected InsertIntoAccountTasklet insertIntoAccountTasklet() {
	    	InsertIntoAccountTasklet tasklet = new InsertIntoAccountTasklet();
	    	return tasklet;
    }
    
    @Bean
    protected ProcessToTrueTasklet processToTrueTasklet() {
	    	ProcessToTrueTasklet tasklet = new ProcessToTrueTasklet();
	    	return tasklet;
    }

    @Bean
    protected Job jobFitness(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("jobFitness", jobRepository)
          .start(step1(jobRepository, transactionManager))
          .next(step2(jobRepository, transactionManager))
          .next(step3(jobRepository, transactionManager))
          .build();
    }
    

}
