package com.bookloom.shared.errors;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestExceptionHandlerAdviceTest {

    private final RestExceptionHandlerAdvice advice = new RestExceptionHandlerAdvice();

    @Test
    public void testHandleGenericException() {
        // Arrange
        Exception exception = new Exception("Generic error message");

        // Act
        ProblemDetail problemDetail = advice.handleException(exception);

        // Assert
        assertNotNull(problemDetail);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), problemDetail.getStatus());
        assertEquals("Generic error message", problemDetail.getDetail());
    }

    @Test
    public void testHandleAuthenticationException() {
        // Arrange
        AuthenticationException authException = new AuthenticationException("Authentication error message") {};

        // Act
        ProblemDetail problemDetail = advice.handleException(authException);

        // Assert
        assertNotNull(problemDetail);
        assertEquals(HttpStatus.UNAUTHORIZED.value(), problemDetail.getStatus());
        assertEquals("Authentication error message", problemDetail.getDetail());
    }
}
