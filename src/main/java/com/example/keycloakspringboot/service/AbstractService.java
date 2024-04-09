package com.example.keycloakspringboot.service;

import com.example.keycloakspringboot.dto.FilterRequest;
import com.example.keycloakspringboot.exception.NotFountException;
import com.example.keycloakspringboot.mapper.MapperService;
import com.example.keycloakspringboot.repository.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public abstract class AbstractService<E, D, I> implements ServiceInterface<D, I> {

    protected final RepositoryService<E, I> repositoryService;
    protected final MapperService<E, D> mapperService;

    @Override
    @Transactional
    public D create(D dto) {
        E entity = mapperService.toEntity(dto);
        E savedEntity = repositoryService.create(entity);
        return mapperService.toDto(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public D find(I id) {
        Optional<E> optionalEntity = repositoryService.find(id);
        E entity = optionalEntity
                .orElseThrow(() -> new NotFountException("id = " + id + " not found"));
        return mapperService.toDto(entity);
    }

    @Override
    @Transactional
    public D update(D dto) {
        E entity = mapperService.toEntity(dto);
        E savedEntity = repositoryService.create(entity);
        return mapperService.toDto(savedEntity);
    }

    @Override
    @Transactional
    public void delete(I id) {
        repositoryService.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<D> findList(FilterRequest filter) {
        return repositoryService.findList(filter)
                .stream()
                .map(mapperService::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<D> findPage(FilterRequest filter, Pageable pageable) {
        return repositoryService.findPage(filter, pageable)
                .map(mapperService::toDto);
    }
}
