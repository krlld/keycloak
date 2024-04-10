package com.example.keycloakspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProjectDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Min(0)
    private BigDecimal budget;

    @NotNull
    private LocalDate startAt;

    @NotNull
    private LocalDate endAt;

    @NotBlank
    private String status;
}
