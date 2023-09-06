package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.dao.UserDAO;
import com.example.bookingmeetingroom.model.CustomUserDetails;
import com.example.bookingmeetingroom.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>optionalUser=userDAO.findByUsername(username);
        User user=optionalUser.get();
        if(optionalUser.isPresent()){
            return new CustomUserDetails(user.getUsername(),user.getPassword(),user.getRole());
        }
        throw new UsernameNotFoundException(username);
    }
    public UserDetails loadUserById(Long userId) {
        Optional<User>optionalUser=userDAO.findById(userId);
        User user=optionalUser.get();
        if(optionalUser.isPresent()){
            return new CustomUserDetails(user.getUsername(),user.getPassword(),user.getRole());
        }
        throw new UsernameNotFoundException(user.getUsername());
    }
}
