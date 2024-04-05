package com.example.keycloakspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProjectDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String title;

    private String description;

    private BigDecimal budget;

    private LocalDate startAt;

    private LocalDate endAt;

    private String status;
}
