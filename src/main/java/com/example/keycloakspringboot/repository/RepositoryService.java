package com.example.keycloakspringboot.repository;

import com.example.keycloakspringboot.dto.FilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RepositoryService<E, I> {

    E create(E entity);

    Optional<E> find(I id);

    E update(E entity);

    void delete(I id);

    List<E> findList(FilterRequest filter);

    Page<E> findPage(FilterRequest filter, Pageable pageable);
}