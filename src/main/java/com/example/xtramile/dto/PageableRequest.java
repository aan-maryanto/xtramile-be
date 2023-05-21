package com.example.xtramile.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageableRequest {

    @Min(value = 0)
    @Builder.Default
    private int page = 0;
    @Min(value = 1)
    @Max(value = 100)
    @Builder.Default
    private int size = 10;
    private String column;
    @Builder.Default
    private Sort.Direction sort = Sort.Direction.DESC;

}
