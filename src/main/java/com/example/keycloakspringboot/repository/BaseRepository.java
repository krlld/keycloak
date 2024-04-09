package com.example.keycloakspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<E, I> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {

}
