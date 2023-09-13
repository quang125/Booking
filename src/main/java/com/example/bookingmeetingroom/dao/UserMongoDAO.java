package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserMongoDAO extends MongoRepository<User,String> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);
}
