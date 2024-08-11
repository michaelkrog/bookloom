package com.bookloom.user;

import com.bookloom.user.models.User;
import com.bookloom.user.models.UserRole;
import com.bookloom.user.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class DataInitializer  {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initData() {
        if(repository.count() == 0) {
            var randomPassword = UUID.randomUUID().toString();
            var user = new User();
            user.setName("Admin");
            user.setEmail("admin@bookloom");
            user.setRoles(Set.of(UserRole.User, UserRole.Admin));
            user.setPassword(passwordEncoder.encode(randomPassword));
            repository.save(user);
            LOG.info("\n\nInitialized admin user.\nPassword: {}\n\n", randomPassword);
        }
    }
}
