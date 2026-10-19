package com.romeuzxg.academy_system.dto.response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
