package com.app.estate_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.estate_api.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{
    
}
