package com.bookloom.user.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {
    private final String token;
}
