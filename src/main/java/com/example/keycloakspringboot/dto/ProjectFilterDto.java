package com.example.keycloakspringboot.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectFilterDto implements FilterRequest {

    private String title;

    private String description;

    private BigDecimal budget;
}
