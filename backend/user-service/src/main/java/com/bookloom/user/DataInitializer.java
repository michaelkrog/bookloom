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
            var admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@bookloom");
            admin.setRoles(Set.of(UserRole.User, UserRole.Admin));
            admin.setPassword(passwordEncoder.encode("AdminTest"));
            repository.save(admin);

            var user = new User();
            user.setName("John Doe");
            user.setEmail("user@bookloom");
            user.setRoles(Set.of(UserRole.User));
            user.setPassword(passwordEncoder.encode("UserTest"));
            repository.save(user);
        }
    }
}
