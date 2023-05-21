package com.example.xtramile.dto;

import com.example.xtramile.entity.AustralianAddress;
import com.example.xtramile.enums.Gender;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PatientDto {

    private String pid;
    private String firstName;
    private String lastName;
    private Date dob;
    private Gender gender;
    private AustralianAddress australianAddress;
    private String phoneNo;
}
