package com.app.estate_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.dto.RentalConverter;
import com.app.estate_api.dto.RentalCreateDto;
import com.app.estate_api.model.Rental;
import com.app.estate_api.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalConverter converter;

    public List<Rental> getRentalList() {
        List<Rental> rentalList = new ArrayList<Rental>();
        
        rentalRepository.findAll().forEach(rentalList::add);

        return rentalList;
    }

    public Optional<Rental> getRental(final Integer id) {
        return rentalRepository.findById(id);
    }

    public Rental saveRental(RentalCreateDto rentalDto) throws Exception {
        Rental savedRental = converter.createFromRentalCreateDto(rentalDto);
        rentalRepository.save(savedRental);
        return savedRental;
    }

    public Rental updateRental(Rental rentalToUpdate, RentalCreateDto rentalDto) throws Exception {
        converter.updateFromRentalCreateDto(rentalToUpdate, rentalDto);
    
        rentalRepository.save(rentalToUpdate);
        return rentalToUpdate;
    }
}
