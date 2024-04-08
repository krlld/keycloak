package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.FilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface AbstractService<D, I> {

    Optional<D> create(D entity);
    Optional<D> find(I id);
    Optional<D> update(D entity);
    void delete(I id);

    List<D> findList(FilterRequest filter);
    Page<D> findPage(FilterRequest filter, Pageable pageable);

}
