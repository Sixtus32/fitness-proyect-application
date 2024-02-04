package com.s6x.fitnessproyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s6x.fitnessproyect.dto.RequestSuscriptorDTO;
import com.s6x.fitnessproyect.dto.ResponseSuscriptorDTO;
import com.s6x.fitnessproyect.service.SubscriberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/")
public class FitnessControllerRest {
	@Autowired
	private SubscriberService subscriberService;

	@Operation(summary = "List all subscribers")
	@ApiResponse(responseCode = "200", description = "Retrieves a list of all subscribers",
	        content = {
	                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseSuscriptorDTO.class)))
	        })
	@GetMapping("subscribers")
	public ResponseEntity<List<ResponseSuscriptorDTO>> getListOfAllSubscribers() {
	    return ResponseEntity.ok(this.subscriberService.getAllSuscriptores());
	}

	@Operation(summary = "Create a new Subscriber")
	@ApiResponse(responseCode = "201", description = "Creates a new subscriber",
	        content = {
	                @Content(mediaType = "application/json", schema = @Schema(implementation = RequestSuscriptorDTO.class))
	        })
	@PostMapping("subscriber")
	@Parameter(name = "subscriberDTO", description = "Body information of the subscriber to create", required = true)
	public ResponseEntity<ResponseSuscriptorDTO> createSubscriber(@RequestBody RequestSuscriptorDTO subscriberDTO) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriberService.createSuscriptor(subscriberDTO));
	}
	
	
	@Operation(summary = "Check by email if subscriber exists")
	@ApiResponse(responseCode = "200", description = "Find out if the subscriber exists",
	        content = {
	                @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
	        })
	@ApiResponse(responseCode = "404", description = "Subscriber not found",
	        content = {
	                @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
	        })
	@GetMapping(value = "subscriber")
	@Parameter(name = "mail", description = "mail of the subscriptor", example = "carlos@example.com")
	public ResponseEntity<Boolean> checkIfExistsByEmail(@RequestParam("mail") String mail) {
	    // Verifica si el subscriptor existe por el correo proporcionado
	    Boolean subscriberExists = subscriberService.checkExistSubscriber(mail);

	    // Determina el código de estado en base a si el subscriptor existe o no
	    HttpStatus status = (subscriberExists) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

	    // Retorna una respuesta con el resultado y el código de estado
	    return new ResponseEntity<>(subscriberExists, status);
	}

}
