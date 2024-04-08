package com.example.keycloakspringboot.controller;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.dto.ProjectFilterDto;
import com.example.keycloakspringboot.service.AbstractService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final AbstractService<ProjectDto, Long> abstractService;

    public ProjectController(@Qualifier("projectService") AbstractService<ProjectDto, Long> abstractService) {
        this.abstractService = abstractService;
    }

    @PreAuthorize("hasAnyRole('hr')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> findAll(@RequestBody ProjectFilterDto projectFilterDto) {
        return abstractService.findList(projectFilterDto);
    }
}
