package com.bookloom.user.services;

import com.bookloom.shared.security.JwtTokenProvider;
import com.bookloom.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Secured("ROLE_USER")
    public String generateJwtTokenForCurrentUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var details = (UserDetails) authentication.getPrincipal();
        return tokenProvider.createToken(details);
    }
}
