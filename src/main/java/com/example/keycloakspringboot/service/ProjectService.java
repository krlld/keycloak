package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.FilterRequest;
import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.entity.Project;
import com.example.keycloakspringboot.mapper.ProjectMapper;
import com.example.keycloakspringboot.repository.RepositoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectService implements AbstractService<ProjectDto, Long> {

    private final RepositoryService repositoryService;
    private final ProjectMapper projectMapper;

    public ProjectService(
            @Qualifier("projectRepositoryService") RepositoryService repositoryService,
            ProjectMapper projectMapper
    ) {
        this.repositoryService = repositoryService;
        this.projectMapper = projectMapper;
    }

    @Override
    public Optional<ProjectDto> create(ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);
        return repositoryService.create(project);
    }

    @Override
    public Optional<ProjectDto> find(Long id) {
        return repositoryService.find(id);
    }

    @Override
    public Optional<ProjectDto> update(ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);
        return repositoryService.update(project);
    }

    @Override
    public void delete(Long id) {
        repositoryService.delete(id);
    }

    @Override
    public List<ProjectDto> findList(FilterRequest filter) {
        return repositoryService.findList(filter);
    }

    @Override
    public Page<ProjectDto> findPage(FilterRequest filter, Pageable pageable) {
        Page<Project> projects = repositoryService.findPage(filter, pageable);
        return projects.map(projectMapper::toProjectDto);
    }
}
