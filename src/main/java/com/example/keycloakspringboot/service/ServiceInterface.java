package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.FilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceInterface<D, I> {

    D create(D entity);

    D find(I id);

    D update(D entity);

    void delete(I id);

    List<D> findList(FilterRequest filter);

    Page<D> findPage(FilterRequest filter, Pageable pageable);

}
