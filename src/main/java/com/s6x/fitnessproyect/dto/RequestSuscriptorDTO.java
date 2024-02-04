package com.s6x.fitnessproyect.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the request for creating a subscriber.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestSuscriptorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Email of the subscriber.
     */
    @Schema(name = "mail", description = "Subscriber mail", example = "mandela@gmail.com")
    private String mail;

    /**
     * Name of the subscriber.
     */
    @Schema(name = "name", description = "Subscriber name", example = "Nelson Mandela")
    private String name;
}
