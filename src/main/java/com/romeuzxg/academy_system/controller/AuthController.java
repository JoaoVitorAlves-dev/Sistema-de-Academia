package com.romeuzxg.academy_system.controller;

import com.romeuzxg.academy_system.dto.request.LoginRequest;
import com.romeuzxg.academy_system.dto.request.RegisterRequest;
import com.romeuzxg.academy_system.dto.response.TokenResponse;
import com.romeuzxg.academy_system.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequestDto) throws Exception {
        return authenticationService.login(loginRequestDto);
    }

}
