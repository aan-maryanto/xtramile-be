package com.example.xtramile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    INTERNAL_ERROR(HttpStatus.SERVICE_UNAVAILABLE,
            "Sorry, there must be something wrong with the service.",
                    "internal_error"),
    INVALID_REQUEST_ERROR(HttpStatus.BAD_REQUEST,
            "The request for %s is invalid, because %s.", // field reason
                    "invalid_request_error"),
    PATIENT_NOT_FOUND(HttpStatus.BAD_REQUEST,
            "Patient not found", //code
                    "patient_not_found");

    private final HttpStatus status;
    private final String message;
    private final String type;

}
