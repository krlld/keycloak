package com.example.keycloakspringboot.repository;

import com.example.keycloakspringboot.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
