package com.bookloom.shared.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * {@code JwtAuthenticationFilter} is a Spring Security filter that processes JWT authentication
 * for incoming HTTP requests. It extracts the JWT token from the "Authorization" header,
 * validates it, and sets the authentication in the Spring Security context.
 * <p>
 * This filter extends {@link OncePerRequestFilter} to ensure that it is invoked once per
 * request and is responsible for processing JWT-based authentication.
 * </p>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtTokenProvider tokenProvider;

    /**
     * Performs the filtering of requests to handle JWT authentication.
     * <p>
     * This method extracts the JWT from the "Authorization" header if present,
     * validates the token, and sets the authentication details in the Spring Security context.
     * </p>
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain to pass the request and response to the next filter
     * @throws ServletException if an error occurs during filtering
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        LOG.debug("Auth Header :  {}", authHeader);

        UserDetails user = null;

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            var token = authHeader.substring(7);
            user = tokenProvider.getUserDetailsFromToken(token);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            LOG.debug("User authenticated [username={}]", user.getUsername());
        }

        filterChain.doFilter(request, response);

    }
}
