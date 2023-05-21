package com.example.xtramile.dto.response;

import com.example.xtramile.dto.PageableResponse;
import com.example.xtramile.dto.PatientDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PatientListResponse {
    private List<PatientDto> list;
    private PageableResponse page;
}
