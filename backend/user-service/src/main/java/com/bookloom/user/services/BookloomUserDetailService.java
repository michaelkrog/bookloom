package com.bookloom.user.services;

import com.bookloom.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BookloomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unable to find user with email '" + username + "'."));

        var grantedAuthorities = new HashSet<GrantedAuthority>();

        user.getRoles()
                .forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
