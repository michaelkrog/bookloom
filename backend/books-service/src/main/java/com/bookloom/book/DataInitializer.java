package com.bookloom.book;

import com.bookloom.book.models.Book;
import com.bookloom.book.repositories.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DataInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private BookRepository repository;


    @PostConstruct
    public void initData() {
        if(repository.count() == 0) {
            var mapper = new ObjectMapper();
            ClassLoader classLoader = getClass().getClassLoader();

            try (var is = classLoader.getResourceAsStream("books.json")) {
                var books = mapper.readValue(is, new TypeReference<List<Book>>() { });
                repository.saveAll(books);
            } catch (IOException ex) {
                LOG.error("Unable to initialize books.");
            }
        }
    }
}
