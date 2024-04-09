package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.entity.Project;
import com.example.keycloakspringboot.mapper.MapperService;
import com.example.keycloakspringboot.repository.RepositoryService;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractService<Project, ProjectDto, Long> {

    public ProjectService(
            RepositoryService<Project, Long> repositoryService,
            MapperService<Project, ProjectDto> mapperService) {
        super(repositoryService, mapperService);
    }
}
