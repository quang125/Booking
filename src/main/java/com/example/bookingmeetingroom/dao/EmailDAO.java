package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDAO extends JpaRepository<Email,Long> {
}
