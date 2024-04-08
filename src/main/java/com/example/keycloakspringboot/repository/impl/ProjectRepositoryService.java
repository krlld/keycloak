package com.example.keycloakspringboot.repository.impl;

import com.example.keycloakspringboot.dto.FilterRequest;
import com.example.keycloakspringboot.entity.Project;
import com.example.keycloakspringboot.repository.ProjectRepository;
import com.example.keycloakspringboot.repository.RepositoryService;
import com.example.keycloakspringboot.spec.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectRepositoryService implements RepositoryService<Project, Long> {

    private final ProjectRepository projectRepository;

    private final SpecificationBuilder<Project> specificationBuilder;

    @Override
    @Transactional
    public Project create(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> find(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public Project update(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findList(FilterRequest filter) {
        Specification<Project> specification = specificationBuilder.build(filter);
        return projectRepository.findAll(specification);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Project> findPage(FilterRequest filter, Pageable pageable) {
        Specification<Project> specification = specificationBuilder.build(filter);
        return projectRepository.findAll(specification, pageable);
    }
}
