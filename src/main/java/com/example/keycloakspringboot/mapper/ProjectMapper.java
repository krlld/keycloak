package com.example.keycloakspringboot.mapper;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.entity.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMapper extends MapperService<Project, ProjectDto> {

    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);
}
