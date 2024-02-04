package com.s6x.fitnessproyect.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.s6x.fitnessproyect.dto.RequestSuscriptorDTO;
import com.s6x.fitnessproyect.dto.ResponseSuscriptorDTO;
import com.s6x.fitnessproyect.entity.SubscriberEntity;
import com.s6x.fitnessproyect.mapper.SubscriberMapper;
import com.s6x.fitnessproyect.repository.SubscriberRepository;

@Service
public class SubscriberService {
	@Autowired
	private SubscriberRepository subscriberRepository;
	@Autowired
	private SubscriberMapper subscriberMapper;
	
	// For Controller
	@Transactional
	public Boolean checkExistSubscriber(String subscriberEmail) {
		return (this.subscriberRepository.countByMailIgnoreCase(subscriberEmail) == 1) ? true : false;
	}
	
	@Transactional
    public ResponseSuscriptorDTO createSuscriptor(RequestSuscriptorDTO requestDTO) {
        // Crear una nueva entidad Suscriptor con los datos del RequestDTO
        SubscriberEntity suscriptorEntity = new SubscriberEntity();
        suscriptorEntity.setMail(requestDTO.getMail());
        suscriptorEntity.setName(requestDTO.getName());
        suscriptorEntity.setPrice(500.0);
        suscriptorEntity.setDate(LocalDate.now());

        // Guardar la entidad en la base de datos
        SubscriberEntity savedSuscriptorEntity = this.subscriberRepository.save(suscriptorEntity);

        // Crear un ResponseDTO con los datos de la entidad guardada
        ResponseSuscriptorDTO responseDTO = new ResponseSuscriptorDTO();
        responseDTO.setId(savedSuscriptorEntity.getId());
        responseDTO.setMail(savedSuscriptorEntity.getMail());
        responseDTO.setName(savedSuscriptorEntity.getName());

        return responseDTO;
    }
    
    @Transactional(readOnly = true)
    public List<ResponseSuscriptorDTO> getAllSuscriptores() {
        // Obtener todos los suscriptores de la base de datos
        List<SubscriberEntity> suscriptores = this.subscriberRepository.findAll();

        // Crear una lista de ResponseDTOs con los datos de las entidades obtenidas
        List<ResponseSuscriptorDTO> responseDTOs = new ArrayList<>();
        for (SubscriberEntity suscriptor : suscriptores) {
            ResponseSuscriptorDTO responseDTO = new ResponseSuscriptorDTO();
            responseDTO.setId(suscriptor.getId());
            responseDTO.setMail(suscriptor.getMail());
            responseDTO.setName(suscriptor.getName());
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }
	
	/*JOB.	Recuperar las suscripciones con proccess a false.*/
    @Transactional(readOnly = true)
	public List<ResponseSuscriptorDTO> getSubscribersNotProcessed() {
		return this.subscriberMapper.subscribersEntitiesToSubscribersDTOs(this.subscriberRepository.findByProcessFalse());
	}
	
	
	// JOB.	Actualizar el campo proccess a true en BBDD.
	@Transactional
	public void updateSubscriberToProcessed(List<ResponseSuscriptorDTO> subscribers) {
		
		for (ResponseSuscriptorDTO subscriberDTO : subscribers) {
			Optional<SubscriberEntity> optSubscriber = this.subscriberRepository.findById(subscriberDTO.getId());
			if(optSubscriber.isPresent()) {
				SubscriberEntity subscriber = optSubscriber.get();
				// Verificó si ya está procesado para evitar actualización innecesarias
				if(!subscriber.isProcess()) {
					subscriber.setProcess(true);
					this.subscriberRepository.save(subscriber);
					System.out.println("PROCESSED : " + subscriber.getName() + " TO " + subscriber.isProcess() );
				}
			}
		}
		
	}
}
