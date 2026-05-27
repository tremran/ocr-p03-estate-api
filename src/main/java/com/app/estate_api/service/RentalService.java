package com.app.estate_api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.dto.RentalConverter;
import com.app.estate_api.dto.RentalCreateDto;
import com.app.estate_api.dto.RentalResponseDto;
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

    public List<RentalResponseDto> getRentalList() {

        Iterable<Rental> rentalList = rentalRepository.findAll();
        List<RentalResponseDto> rentalResponseList = converter.convertListToRentalResponseDto(rentalList);

        return rentalResponseList;
    }

    public RentalResponseDto getRentalResponse(final Integer id) throws Exception{
        Rental rental =getRental(id);

        return converter.convertToRentalResponseDto(rental);
    }

    public Rental getRental(final Integer id) throws Exception{
        Optional<Rental> optRental = rentalRepository.findById(id);

        if (optRental.isPresent())
        {
            return optRental.get();
        }
        throw new Exception("Rental [" + id + "] not found");
    }

    public RentalResponseDto saveRental(RentalCreateDto rentalDto) throws Exception {
        Rental savedRental = converter.createFromRentalCreateDto(rentalDto);
        rentalRepository.save(savedRental);
        return converter.convertToRentalResponseDto(savedRental);
    }

    public Rental updateRental(Rental rentalToUpdate, RentalCreateDto rentalDto) throws Exception {
        converter.updateFromRentalCreateDto(rentalToUpdate, rentalDto);
        rentalToUpdate.setUpdatedAt(new Date());

        rentalRepository.save(rentalToUpdate);
        return rentalToUpdate;
    }
}
