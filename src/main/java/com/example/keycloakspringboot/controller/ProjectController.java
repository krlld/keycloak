package com.example.keycloakspringboot.controller;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.dto.ProjectFilterDto;
import com.example.keycloakspringboot.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProjectController {

    private final ServiceInterface<ProjectDto, Long> serviceInterface;

    @PreAuthorize("hasAnyRole('hr')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> findAll(@RequestBody ProjectFilterDto projectFilterDto) {
        return serviceInterface.findList(projectFilterDto);
    }
}
