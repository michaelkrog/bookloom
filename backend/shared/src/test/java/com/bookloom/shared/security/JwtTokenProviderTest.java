package com.bookloom.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenProvider = new JwtTokenProvider();
    }

    @Test
    public void testCreateToken() {
        // Arrange
        UserDetails userDetails = User.builder()
                .username("testuser")
                .password("password")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        // Act
        String token = jwtTokenProvider.createToken(userDetails);

        // Assert
        assertNotNull(token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtTokenProvider.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals("testuser", claims.getSubject());
        assertTrue(claims.getExpiration().after(new Date()));
        List<String> roles = claims.get("roles", List.class);
        assertTrue(roles.contains("ROLE_USER"));
    }

    @Test
    public void testGetUserDetailsFromToken() {
        // Arrange
        String token = jwtTokenProvider.createToken(new User("testuser", "password", List.of(new SimpleGrantedAuthority("ROLE_USER"))));

        // Act
        UserDetails userDetails = jwtTokenProvider.getUserDetailsFromToken(token);

        // Assert
        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testGetUserDetailsFromToken_WithInvalidToken() {
        // Act
        UserDetails userDetails = jwtTokenProvider.getUserDetailsFromToken("invalid-token");

        // Assert
        assertNull(userDetails);
    }

    @Test
    public void testGetEmailFromToken() {
        // Arrange
        String token = jwtTokenProvider.createToken(new User("testuser", "password", List.of(new SimpleGrantedAuthority("ROLE_USER"))));

        // Act
        String email = jwtTokenProvider.getEmailFromToken(token);

        // Assert
        assertNull(email); // Email is not set in this implementation
    }

    @Test
    public void testValidateToken() {
        // Arrange
        String token = jwtTokenProvider.createToken(new User("testuser", "password", List.of(new SimpleGrantedAuthority("ROLE_USER"))));

        // Act
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testValidateToken_WithExpiredToken() {
        // Arrange
        String token = Jwts.builder()
                .setSubject("testuser")
                .setExpiration(new Date(System.currentTimeMillis() - 1000)) // Token expired
                .signWith(jwtTokenProvider.getSigningKey())
                .compact();

        // Act
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void testRolesFromAuthorities() {
        // Arrange
        List<SimpleGrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN"));

        // Act
        String[] roles = jwtTokenProvider.rolesFromAuthorities(authorities);

        // Assert
        assertNotNull(roles);
        assertTrue(Arrays.asList(roles).contains("ROLE_USER"));
        assertTrue(Arrays.asList(roles).contains("ROLE_ADMIN"));
    }

    @Test
    public void testAuthoritiesFromRoles() {
        // Arrange
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

        // Act
        List<GrantedAuthority> authorities = jwtTokenProvider.authoritiesFromRoles(roles);

        // Assert
        assertNotNull(authorities);
        assertTrue(authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        assertTrue(authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
    }
}
