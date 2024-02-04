package com.s6x.fitnessproyect.job;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.s6x.fitnessproyect.service.ClientAccountService;
import com.s6x.fitnessproyect.service.SubscriberService;

public class InsertIntoAccountTasklet implements Tasklet{
	@Autowired
	private ClientAccountService accountService;
	@Autowired
	private SubscriberService subscriberService;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SUSCRIPCIONES INSERTADOS EN LA TABLA CUENTAS (la de Llados)");
		this.accountService.insertIntoLladosAccount(this.subscriberService.getSubscribersNotProcessed());
		return RepeatStatus.FINISHED;
	}

}
