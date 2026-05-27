package com.app.estate_api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    
    @Autowired
    private RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<HashMap<String, List<RentalResponseDto>>> list() {
        List<RentalResponseDto> rentalList = rentalService.getRentalList();
        HashMap<String, List<RentalResponseDto>> rentalListMap = new HashMap<String, List<RentalResponseDto>>();

        rentalListMap.put("rentals", rentalList);
        return ResponseEntity.ok(rentalListMap);
    }

    @GetMapping("/{rental_id}")
    public ResponseEntity<RentalResponseDto> read(@PathVariable("rental_id") final Integer id) throws Exception {
        RentalResponseDto rental = rentalService.getRentalResponse(id);
        return ResponseEntity.ok(rental);
    }

    @PostMapping("")
    public ResponseEntity<HashMap<String,String>> create(@RequestBody RentalCreateDto rentalCreateDto) throws Exception {
        rentalService.saveRental(rentalCreateDto);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Rental created !");
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{rental_id}")
    public ResponseEntity<HashMap<String,String>> updateRental(@PathVariable("rental_id") final Integer id, @RequestBody RentalCreateDto rentalCreateDto) throws Exception{
        Rental rental = rentalService.getRental(id);
            
        rentalService.updateRental(rental, rentalCreateDto);
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Rental updated !");
        return ResponseEntity.ok(message);
    }
    
}
