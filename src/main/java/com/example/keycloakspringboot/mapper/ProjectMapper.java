package com.example.keycloakspringboot.mapper;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.entity.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMapper {

    ProjectDto toProjectDto(Project project);

    Project toProject(ProjectDto projectDto);
}
