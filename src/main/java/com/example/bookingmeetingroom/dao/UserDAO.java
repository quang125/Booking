package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDAO extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);
}
