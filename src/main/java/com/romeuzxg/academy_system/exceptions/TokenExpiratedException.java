package com.romeuzxg.academy_system.exceptions;

public class TokenExpiratedException extends RuntimeException {
    public TokenExpiratedException(String message) {
        super(message);
    }
}
