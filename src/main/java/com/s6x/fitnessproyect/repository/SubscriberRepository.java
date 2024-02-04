package com.s6x.fitnessproyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.s6x.fitnessproyect.entity.SubscriberEntity;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long>{
    @Query("SELECT COUNT(s) FROM SubscriberEntity s WHERE UPPER(s.mail) = UPPER(?1)")
    Integer countByMailIgnoreCase(String mail);
    
    SubscriberEntity findSubscriberByMail(String mail);
    
    @Query("SELECT s FROM SubscriberEntity s WHERE s.process = false")
    List<SubscriberEntity> findByProcessFalse();

}
