package com.app.estate_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.estate_api.dto.RentalCreateDto;
import com.app.estate_api.model.Rental;
import com.app.estate_api.service.RentalService;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {
    
    @Autowired
    private RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<List<Rental>> list() {
        List<Rental> rentalList = rentalService.getRentalList();

        return ResponseEntity.ok(rentalList);
    }

    @GetMapping("/{rental_id}")
    public ResponseEntity<Rental> read(@PathVariable("rental_id") final Integer id) {
        Optional<Rental> rental = rentalService.getRental(id);
        if (rental.isPresent())
        {
            return ResponseEntity.ok(rental.get());
        }
        return null;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Rental> create(@RequestBody RentalCreateDto rentalDto) throws Exception {
        return new ResponseEntity<Rental>(rentalService.saveRental(rentalDto), HttpStatus.CREATED);
    }

    @PutMapping("/{rental_id}")
    public ResponseEntity<Rental> updateRental(@PathVariable("rental_id") final Integer id, @RequestBody RentalCreateDto rentalCreateDto) throws UsernameNotFoundException{
        Optional<Rental> rentalOpt = rentalService.getRental(id);
        if(rentalOpt.isPresent()) {

            Rental currentRental = rentalOpt.get();
            
            rentalService.updateRental(currentRental, rentalCreateDto);
            return ResponseEntity.ok(currentRental);
        } else {
            return null;
        }
    }
    
}
