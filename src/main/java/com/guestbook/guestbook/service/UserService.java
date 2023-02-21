package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.UserRegistrationDto;
import com.guestbook.guestbook.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);

    boolean getUserByEmail(String email);
}