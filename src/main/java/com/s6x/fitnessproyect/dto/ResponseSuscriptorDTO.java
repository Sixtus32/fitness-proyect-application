package com.s6x.fitnessproyect.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the response for a subscriber.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSuscriptorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID of the subscriber.
     */
    @Schema(name = "id", description = "Subscriber ID", example = "1")
    private Long id;

    /**
     * Email of the subscriber.
     */
    @Schema(name = "mail", description = "Subscriber email", example = "mandela@gmail.com")
    private String mail;

    /**
     * Name of the subscriber.
     */
    @Schema(name = "name", description = "Subscriber name", example = "Nelson Mandela")
    private String name;
}
