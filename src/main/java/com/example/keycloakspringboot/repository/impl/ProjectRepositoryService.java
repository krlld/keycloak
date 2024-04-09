package com.example.keycloakspringboot.repository.impl;

import com.example.keycloakspringboot.entity.Project;
import com.example.keycloakspringboot.repository.AbstractRepositoryService;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Repository;

@Repository
@SuperBuilder
public class ProjectRepositoryService extends AbstractRepositoryService<Project, Long> {

}
