package com.example.xtramile.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AustralianAddress implements Serializable {
    private String address;
    private String suburb;
    private String state;
    private String postcode;
}
