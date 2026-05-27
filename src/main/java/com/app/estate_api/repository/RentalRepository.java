package com.app.estate_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.estate_api.model.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer>{
    
}
