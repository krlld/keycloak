package com.example.keycloakspringboot.service.impl;

import com.example.keycloakspringboot.dto.ProjectDto;
import com.example.keycloakspringboot.mapper.ProjectMapper;
import com.example.keycloakspringboot.repository.ProjectRepository;
import com.example.keycloakspringboot.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }
}
