package com.s6x.fitnessproyect.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUBSCRIBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "MAIL", nullable = true)
	private String mail;
	@Column(name = "PRICE", nullable = false)
	private Double price;
	@Column(name = "DATE", nullable = false)
	private LocalDate date;
	@Column(name = "PROCESS", nullable = false)
	@Builder.Default
	private boolean process = false;
}
