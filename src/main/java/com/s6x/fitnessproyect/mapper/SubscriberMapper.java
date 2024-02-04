package com.s6x.fitnessproyect.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.s6x.fitnessproyect.dto.ResponseSuscriptorDTO;
import com.s6x.fitnessproyect.entity.SubscriberEntity;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {
	// Obtener un list de DTOs apartir de una de Entities
	List<ResponseSuscriptorDTO> subscribersEntitiesToSubscribersDTOs(List<SubscriberEntity> subscriberEntities);
}
