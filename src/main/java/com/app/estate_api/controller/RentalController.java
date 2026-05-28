package com.app.estate_api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.RentalCreateDto;
import com.app.estate_api.dto.RentalResponseDto;
import com.app.estate_api.model.Rental;
import com.app.estate_api.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rental", description = "manage rentals - a valid jwt auth token is required ")
public class RentalController {
    
    @Autowired
    private RentalService rentalService;

    @GetMapping("")
    @Operation(summary="gets all rentals")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array= @ArraySchema(schema = @Schema(implementation = RentalResponseDto.class))) }),
        @ApiResponse(responseCode = "401", description = "missing token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "404", description = "rental not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
	})
    public ResponseEntity<HashMap<String, List<RentalResponseDto>>> list() {
        List<RentalResponseDto> rentalList = rentalService.getRentalList();
        HashMap<String, List<RentalResponseDto>> rentalListMap = new HashMap<String, List<RentalResponseDto>>();

        rentalListMap.put("rentals", rentalList);
        return ResponseEntity.ok(rentalListMap);
    }

    @GetMapping("/{rental_id}")
    @Operation(summary="gets a specific rental for given id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDto.class)) }),
        @ApiResponse(responseCode = "401", description = "missing token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "404", description = "rental not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
	})
    public ResponseEntity<RentalResponseDto> read(@PathVariable("rental_id") final Integer id) throws Exception {
        RentalResponseDto rental = rentalService.getRentalResponse(id);
        return ResponseEntity.ok(rental);
    }

    @PostMapping(consumes = "application/json")
    @Operation(summary="creates a new rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "rental created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDto.class)) }),
        @ApiResponse(responseCode = "401", description = "missing token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "404", description = "rental not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
	})
    public ResponseEntity<HashMap<String,String>> create(@RequestBody RentalCreateDto rentalCreateDto) throws Exception {
        rentalService.saveRental(rentalCreateDto);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Rental created !");
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{rental_id}")
    @Operation(summary="updates an existing rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "rental updated"),
        @ApiResponse(responseCode = "401", description = "missing token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "403", description = "invalid token", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = "404", description = "rental not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }),
	})
    public ResponseEntity<HashMap<String,String>> updateRental(@PathVariable("rental_id") final Integer id, @RequestBody RentalCreateDto rentalCreateDto) throws Exception{
        Rental rental = rentalService.getRental(id);
            
        rentalService.updateRental(rental, rentalCreateDto);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Rental updated !");
        return ResponseEntity.ok(message);
    }
    
}
