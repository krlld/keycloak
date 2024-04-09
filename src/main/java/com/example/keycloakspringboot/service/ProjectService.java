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
import org.springframework.transaction.annotation.Transactional;
import com.example.keycloakspringboot.exception.NotFountException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService implements AbstractService<ProjectDto, Long> {

    private final RepositoryService<Project, Long> repositoryService;

    private final ProjectMapper projectMapper;

    public ProjectService(
            @Qualifier("projectRepositoryService") RepositoryService<Project, Long> repositoryService,
            ProjectMapper projectMapper
    ) {
        this.repositoryService = repositoryService;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public ProjectDto create(ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);
        return projectMapper.toProjectDto(repositoryService.create(project));
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDto find(Long id) {
        Optional<Project> optionalProject = repositoryService.find(id);
        Project project = optionalProject
                .orElseThrow(() -> new NotFountException("Project with id = " + id + " not found"));
        return projectMapper.toProjectDto(project);
    }

    @Override
    @Transactional
    public ProjectDto update(ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);
        return projectMapper.toProjectDto(repositoryService.update(project));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryService.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findList(FilterRequest filter) {
        return repositoryService.findList(filter)
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDto> findPage(FilterRequest filter, Pageable pageable) {
        return repositoryService.findPage(filter, pageable)
                .map(projectMapper::toProjectDto);
    }
}
