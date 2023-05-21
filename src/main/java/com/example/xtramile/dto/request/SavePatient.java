package com.example.xtramile.dto.request;

import com.example.xtramile.entity.AustralianAddress;
import com.example.xtramile.enums.Gender;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SavePatient {

    private String pid;
    private String firstName;
    private String lastName;
    private Date dob;
    private Gender gender;
    private AustralianAddress australianAddress;
    private String phoneNo;

}
