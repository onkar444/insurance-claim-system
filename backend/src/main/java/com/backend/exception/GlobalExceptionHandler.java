package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClaimNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleClaimNotFoundException (Exception ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException (Exception ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlePolicyNotFoundException (Exception ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
//
//    @ExceptionHandler(AuthorizationDeniedException.class)
//    public ResponseEntity<?> handleAuthroizationDeniedException(Exception ex){
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<?> handleAuthenticationException(Exception ex){
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(ex.getMessage());
//    }
}
