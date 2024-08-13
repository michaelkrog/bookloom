package com.bookloom.book.repositories;

import com.bookloom.book.models.Book;
import com.bookloom.book.models.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

@DataMongoTest(properties = {"spring.data.mongodb.uri=mongodb://localhost:27017/bookloom-integration-test"})
public class BookRepositoryIntegrationTest {

    @Autowired
    BookRepository repository;

    @TestConfiguration
    @SpringBootApplication
    static class Config {

        @Autowired
        private MongoTemplate mongoTemplate;

    }

    @AfterEach
    public void init() {
        repository.deleteAll();
    }

    @Test
    public void findAllSearchByAuthor() {
        // Arrange
        var book = new Book();
        book.setAuthor("John Doe");
        repository.save(book);

        // Act
        var result = repository.findAll(List.of("John Doe"), null , 0, Integer.MAX_VALUE, PageRequest.of(0, 1));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("John Doe", result.getContent().get(0).getAuthor());
    }

    @Test
    public void findAllSearchByCategory() {
        // Arrange
        var book = new Book();
        book.setCategory(Category.Science);
        repository.save(book);

        // Act
        var result = repository.findAll(null, List.of(Category.Science) , 0, Integer.MAX_VALUE, PageRequest.of(0, 1));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals(Category.Science, result.getContent().get(0).getCategory());
    }


    @Test
    public void findAllSearchByPrice() {
        // Arrange
        var book = new Book();
        book.setPrice(100);
        repository.save(book);

        // Act
        var result = repository.findAll(null, null, 99, 101, PageRequest.of(0, 1));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals(100, result.getContent().get(0).getPrice());
    }

    @Test
    public void findAllSearchBySpecific() {
        // Arrange
        var book = new Book();
        book.setAuthor("John Doe");
        book.setCategory(Category.Science);
        book.setPrice(100);
        repository.save(book);

        var book2 = new Book();
        book2.setAuthor("Bob Johnson");
        book2.setCategory(Category.Science);
        book2.setPrice(140);
        repository.save(book2);

        var book3 = new Book();
        book3.setAuthor("Carol Lee");
        book3.setCategory(Category.Science);
        book3.setPrice(90);
        repository.save(book3);

        var book4 = new Book();
        book4.setAuthor("David Brown");
        book4.setCategory(Category.Other);
        book4.setPrice(85);
        repository.save(book4);

        var book5 = new Book();
        book5.setAuthor("Ella White");
        book5.setCategory(Category.Other);
        book5.setPrice(110);
        repository.save(book5);

        var book6 = new Book();
        book6.setAuthor("Frank Miller");
        book6.setCategory(Category.SelfHelp);
        book6.setPrice(75);
        repository.save(book6);

        var book7 = new Book();
        book7.setAuthor("Grace Wilson");
        book7.setCategory(Category.SelfHelp);
        book7.setPrice(95);
        repository.save(book7);

        var book8 = new Book();
        book8.setAuthor("Henry Davis");
        book8.setCategory(Category.Nature);
        book8.setPrice(100);
        repository.save(book8);

        var book9 = new Book();
        book9.setAuthor("Jack Turner");
        book9.setCategory(Category.Nature);
        book9.setPrice(130);
        repository.save(book9);

        var book10 = new Book();
        book10.setAuthor("Jack Turner");
        book10.setCategory(Category.Poetry);
        book10.setPrice(65);
        repository.save(book10);

        // Act
        var result = repository.findAll(List.of("Jack Turner", "Ella White"),
                List.of(Category.Poetry, Category.Other),
                50, 111,
                PageRequest.of(0, 20, Sort.Direction.ASC, "author"));

        // Assert
        assertEquals(2, result.getTotalElements());
        assertEquals("Ella White", result.getContent().get(0).getAuthor());
        assertEquals(Category.Other, result.getContent().get(0).getCategory());
        assertEquals(110, result.getContent().get(0).getPrice());
        assertEquals("Jack Turner", result.getContent().get(1).getAuthor());
        assertEquals(Category.Poetry, result.getContent().get(1).getCategory());
        assertEquals(65, result.getContent().get(1).getPrice());
    }

}
