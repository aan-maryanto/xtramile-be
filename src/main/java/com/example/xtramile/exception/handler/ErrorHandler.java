package com.example.xtramile.exception.handler;

import com.example.xtramile.exception.BussinessException;
import com.example.xtramile.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<ErrorResponse> bussinessException(BussinessException ex) {
        return ResponseEntity.status(ex.getError().getStatus())
                .body(ErrorResponse.builder()
                        .error(ErrorResponse.Error.builder()
                                .code(String.valueOf(ex.getError().getStatus().value()))
                                .message(Optional.ofNullable(ex.getMessage())
                                        .orElse(ex.getError().getMessage()))
                                .type(ex.getError().getType())
                                .build())
                        .build());
    }

}
