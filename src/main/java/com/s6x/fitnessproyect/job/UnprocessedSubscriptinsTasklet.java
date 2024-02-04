package com.s6x.fitnessproyect.job;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.s6x.fitnessproyect.dto.ResponseSuscriptorDTO;
import com.s6x.fitnessproyect.service.SubscriberService;

public class UnprocessedSubscriptinsTasklet implements Tasklet {
	@Autowired
	private SubscriberService subscriberService;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SUSCRIPCIONES CON PROCCESS = FALSE.");
		List<ResponseSuscriptorDTO> subs = this.subscriberService.getSubscribersNotProcessed();
		for (ResponseSuscriptorDTO subscriberDTO : subs) {
			System.out.println("SUBSCRIBER." + subscriberDTO.getName() + " PROCESS IS FALSE" );
		}
		return RepeatStatus.FINISHED;
	}

}
