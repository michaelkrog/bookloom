package com.bookloom.shared.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Provider for handling JWT tokens.
 * This class provides methods to create a JWT token,
 * extract user details from a token, validate the token,
 * and extract email from the token.
 */
@Service
public class JwtTokenProvider {
    private static final long HOUR = 3600; // An hour in seconds
    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String secretKey = "TUluTm8xb0ppUVRUa21kdTluRFBVQ3Q5SEtaNmREdTVCdVpTSmZRUjFDVkNBVlRKZ2g=";

    /**
     * Creates a JWT token for the given user details.
     *
     * @param userDetails User details to be included in the token.
     * @return The created JWT token as a string.
     */
    public String createToken(UserDetails userDetails) {
        var key = getSigningKey();
        Instant expireDate = Instant.now().plusSeconds(HOUR * 3);
        return Jwts.builder()
                .setExpiration(Date.from(expireDate))
                .setSubject(userDetails.getUsername())
                .claim("roles", rolesFromAuthorities(userDetails.getAuthorities()))
                .signWith(key)
                .compact();
    }

    /**
     * Extracts user details from the given JWT token.
     *
     * @param authToken The JWT token.
     * @return User details extracted from the token, or null if the token is invalid.
     */
    public UserDetails getUserDetailsFromToken(String authToken) {
        Claims claims = parseClaims(authToken);
        if (claims == null) {
            return null;
        }
        return new User(claims.getSubject(), authToken,
                authoritiesFromRoles(claims.get("roles", List.class)));
    }

    /**
     * Extracts email from the given JWT token.
     *
     * @param authToken The JWT token.
     * @return The email extracted from the token, or null if the token is invalid.
     */
    public String getEmailFromToken(String authToken) {
        Claims claims = parseClaims(authToken);
        if (claims == null) {
            return null;
        }
        return (String) claims.get("email");
    }

    /**
     * Validates the given JWT token.
     *
     * @param authToken The JWT token to be validated.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateToken(String authToken) {
        Claims claims = parseClaims(authToken);
        if (claims == null) {
            return false;
        }
        Date expires = claims.getExpiration();
        Date now = new Date();
        return expires == null || expires.after(now);
    }

    /**
     * Converts a collection of granted authorities to a string array of roles.
     *
     * @param auths Collection of granted authorities.
     * @return Array of roles as strings.
     */
    private String[] rolesFromAuthorities(Collection<? extends GrantedAuthority> auths) {
        return auths.stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
    }

    /**
     * Converts a list of role strings to a list of granted authorities.
     *
     * @param roles List of role strings.
     * @return List of granted authorities.
     */
    private List<GrantedAuthority> authoritiesFromRoles(List<String> roles) {
        List<GrantedAuthority> auths = new ArrayList<>();
        roles.forEach(role -> auths.add(new SimpleGrantedAuthority(role)));
        return auths;
    }

    /**
     * Parses claims from the given JWT token.
     *
     * @param authToken The JWT token.
     * @return Claims from the token, or null if parsing fails.
     */
    private Claims parseClaims(String authToken) {
        if (authToken == null) {
            return null;
        }
        try {
            var key = getSigningKey();
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken).getBody();
        } catch (JwtException e) {
            LOG.debug("Invalid JWT token. ", e);
            return null;
        }
    }

    /**
     * Retrieves the signing key for JWT token validation.
     *
     * @return The signing key.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
