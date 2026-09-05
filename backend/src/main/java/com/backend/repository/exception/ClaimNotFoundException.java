package com.backend.repository.exception;

public class ClaimNotFoundException extends RuntimeException{
    public ClaimNotFoundException(String mesasge) {
        super(mesasge);
    }
}
