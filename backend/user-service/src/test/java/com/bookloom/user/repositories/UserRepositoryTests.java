package com.bookloom.user.repositories;

import dk.tryg.codetest.michaelkrog.user.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserRepositoryTests {
    @Autowired
    UserRepository repository;

    @Test
    void readsFirstPageCorrectly() {

        Page<User> persons = repository.findAll(PageRequest.of(0, 10));
        assertThat(persons.isFirst()).isTrue();
    }
}


