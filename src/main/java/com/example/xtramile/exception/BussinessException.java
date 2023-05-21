package com.example.xtramile.exception;

import com.example.xtramile.enums.ResponseEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BussinessException extends RuntimeException {

    private final ResponseEnum error;
    private final String message;

    public BussinessException(ResponseEnum error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public BussinessException(ResponseEnum error) {
        this(error, error.getMessage());
    }

}
