package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDAO extends MongoRepository<Email,String> {
}
