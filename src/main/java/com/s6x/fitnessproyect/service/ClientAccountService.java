package com.s6x.fitnessproyect.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s6x.fitnessproyect.dto.ResponseSuscriptorDTO;
import com.s6x.fitnessproyect.entity.ClientAccountEntity;
import com.s6x.fitnessproyect.entity.SubscriberEntity;
import com.s6x.fitnessproyect.repository.ClientAccountRepository;
import com.s6x.fitnessproyect.repository.SubscriberRepository;

@Service
public class ClientAccountService {

    @Autowired
    private ClientAccountRepository accountRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    // MEJORA** Teniendo en cuenta que lo que ofrece Llados cuesta 250€ ej.
    public void insertIntoLladosAccount(List<ResponseSuscriptorDTO> subscriberDTOs) {
        // COSTE
        final double COURSE_COST = 250.0;

        for (ResponseSuscriptorDTO subscriberDTO : subscriberDTOs) {
            // Recupera el SubscriberEntity utilizando el id del SubscriberDTO
            Optional<SubscriberEntity> optSubscriber = subscriberRepository.findById(subscriberDTO.getId());

            // Verifica si el SubscriberEntity está presente antes de continuar
            optSubscriber.ifPresent(subscriberEntity -> {
                // Actualiza el precio del SubscriberEntity
                subscriberEntity.setPrice(subscriberEntity.getPrice() - COURSE_COST);
                // Guarda el SubscriberEntity actualizado
                subscriberRepository.save(subscriberEntity);

                // Crea una nueva entidad ClientAccount
                ClientAccountEntity accountTransaction = ClientAccountEntity.builder()
                        .concept(subscriberEntity.getId() + "-" + subscriberEntity.getName())
                        .money(COURSE_COST)
                        .date(LocalDate.now())
                        .build();

                // Guarda la nueva transacción en la cuenta de Llados
                accountRepository.save(accountTransaction);
                System.out.println("CREATED ACCOUNT WITH LLADOS FOR "
                + subscriberEntity.getName() + "\n\t\t\t WAITING TO BE PROCESS");
            });
        }
    }
}
