package com.example.keycloakspringboot.repository.impl;

import com.example.keycloakspringboot.entity.Project;
import com.example.keycloakspringboot.repository.AbstractRepositoryService;
import com.example.keycloakspringboot.repository.BaseRepository;
import com.example.keycloakspringboot.spec.SpecificationBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepositoryService extends AbstractRepositoryService<Project, Long> {

    public ProjectRepositoryService(
            BaseRepository<Project, Long> repository,
            SpecificationBuilder<Project> specificationBuilder) {
        super(repository, specificationBuilder);
    }
}
