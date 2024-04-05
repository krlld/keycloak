package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();
}
