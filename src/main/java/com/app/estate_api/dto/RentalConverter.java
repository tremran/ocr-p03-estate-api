package com.app.estate_api.dto;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.estate_api.model.Rental;
import com.app.estate_api.model.User;
import com.app.estate_api.service.UserService;

@Service
public class RentalConverter {
    @Autowired
    private UserService userService;

    public Rental createFromRentalCreateDto(RentalCreateDto rentalCreateDto) throws Exception
    {
        Rental rental = new Rental();

        rental.setCreatedAt(new Date());
        rental.setUpdatedAt(new Date());
        this.updateFromRentalCreateDto(rental, rentalCreateDto);

        return rental;
    }
    public void updateFromRentalCreateDto(Rental rental, RentalCreateDto rentalCreateDto) throws Exception
    {
        
        if (rentalCreateDto.getName() != null) {
            rental.setName(rentalCreateDto.getName());
        }
        if (rentalCreateDto.getSurface() != null) {
            rental.setSurface(rentalCreateDto.getSurface());
        }
        if (rentalCreateDto.getPrice() != null) {
            rental.setPrice(rentalCreateDto.getPrice());
        }
        if (rentalCreateDto.getPicture() != null) {
            rental.setPicture(rentalCreateDto.getPicture());
        }
        if (rentalCreateDto.getDescription() != null) {
            rental.setDescription(rentalCreateDto.getDescription());
        }

        if (rentalCreateDto.getOwnerId() != null) {
            rental.setOwner(getOwner(rentalCreateDto.getOwnerId()));
        }
    }

    public RentalCreateDto convertToRentalCreateDto(Rental rental) {

        RentalCreateDto rentalCreateDto = new RentalCreateDto();
        
        rentalCreateDto.setId(rental.getId());
        rentalCreateDto.setName(rental.getName());
        rentalCreateDto.setSurface(rental.getSurface());
        rentalCreateDto.setPrice(rental.getPrice());
        rentalCreateDto.setPicture(rental.getPicture());
        rentalCreateDto.setDescription(rental.getDescription());

        rentalCreateDto.setOwnerId(rental.getOwner().getId());

        Format dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        rentalCreateDto.setCreated_at(dateFormatter.format(rental.getCreatedAt()));
        rentalCreateDto.setUdpated_at(dateFormatter.format(rental.getUpdatedAt()));

        return rentalCreateDto;
    }
    public RentalResponseDto convertToRentalResponseDto(Rental rental) {

        RentalResponseDto rentalResponseDto = new RentalResponseDto();
        
        rentalResponseDto.setId(rental.getId());
        rentalResponseDto.setName(rental.getName());
        rentalResponseDto.setSurface(rental.getSurface());
        rentalResponseDto.setPrice(rental.getPrice());
        rentalResponseDto.setPicture(rental.getPicture());
        rentalResponseDto.setDescription(rental.getDescription());

        rentalResponseDto.setOwner_id(rental.getOwner().getId());

        Format dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        rentalResponseDto.setCreated_at(dateFormatter.format(rental.getCreatedAt()));
        rentalResponseDto.setUpdated_at(dateFormatter.format(rental.getUpdatedAt()));

        return rentalResponseDto;
    }

    public List<RentalResponseDto> convertListToRentalResponseDto(Iterable<Rental> rentalList) {
        ArrayList<RentalResponseDto> rentalResponseDtoList = new ArrayList<RentalResponseDto>();

        for (Rental currentRental: rentalList ) {
            rentalResponseDtoList.add(convertToRentalResponseDto(currentRental));
        }
        return rentalResponseDtoList;
        
    }
    private User getOwner(Integer ownerId) throws Exception
    {
        Optional<User> ownerOpt = userService.getUser(ownerId);

        if (ownerOpt.isPresent())
        {
            return ownerOpt.get();
        }
        throw new Exception("Owner not found with id [" + ownerId + "]");
    }
}
