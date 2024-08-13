package com.bookloom.shared.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtTokenProvider tokenProvider;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(mock(SecurityContext.class));
    }

    @Test
    public void testDoFilterInternal_WithValidToken() throws Exception {
        // Arrange
        String token = "valid-token";
        String authHeader = "Bearer " + token;
        UserDetails userDetails = User.builder()
                .username("testuser")
                .password("password")
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        when(request.getHeader("Authorization")).thenReturn(authHeader);
        when(tokenProvider.getUserDetailsFromToken(token)).thenReturn(userDetails);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        ArgumentCaptor<Authentication> authenticationCaptor = ArgumentCaptor.forClass(Authentication.class);
        verify(SecurityContextHolder.getContext()).setAuthentication(authenticationCaptor.capture());

        Authentication authentication = authenticationCaptor.getValue();
        assertNotNull(authentication);
        assertEquals(userDetails, authentication.getPrincipal());
        assertEquals(userDetails.getAuthorities().stream().findFirst(), authentication.getAuthorities().stream().findFirst());
        assertNotNull(authentication.getDetails());
        assertTrue(authentication.getDetails() instanceof WebAuthenticationDetails);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WithInvalidToken() throws Exception {
        // Arrange
        String authHeader = "Bearer invalid-token";
        when(request.getHeader("Authorization")).thenReturn(authHeader);
        when(tokenProvider.getUserDetailsFromToken("invalid-token")).thenReturn(null);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(SecurityContextHolder.getContext(), never()).setAuthentication(any());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_NoToken() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(SecurityContextHolder.getContext(), never()).setAuthentication(any());
        verify(filterChain).doFilter(request, response);
    }
}
