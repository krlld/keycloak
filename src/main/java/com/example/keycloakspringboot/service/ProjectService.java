package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.entity.Project;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

@Service
@SuperBuilder
public class ProjectService extends AbstractService<Project, ProjectDto, Long> {

}
