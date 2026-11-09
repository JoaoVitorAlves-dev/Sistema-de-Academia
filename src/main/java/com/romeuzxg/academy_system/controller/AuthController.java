package com.romeuzxg.academy_system.controller;

import com.romeuzxg.academy_system.dto.request.AlunoRequest;
import com.romeuzxg.academy_system.dto.request.LoginRequest;
import com.romeuzxg.academy_system.dto.request.RefreshTokenRequest;
import com.romeuzxg.academy_system.dto.request.RegisterRequest;
import com.romeuzxg.academy_system.dto.response.RefreshTokenResponse;
import com.romeuzxg.academy_system.dto.response.TokenResponse;
import com.romeuzxg.academy_system.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody @Valid RefreshTokenRequest req, Authentication auth) {
        return ResponseEntity.ok(authenticationService.refresh(req, auth));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Void> updateById(@PathVariable Long id, @RequestBody @Valid AlunoRequest req, Authentication auth) {
        authenticationService.updateById(id, req, auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequestDto) throws Exception {
        return authenticationService.login(loginRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, Authentication auth) {
        authenticationService.deleteById(id, auth);
        return ResponseEntity.noContent().build();
    }

}
