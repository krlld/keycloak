package com.example.keycloakspringboot.repository;

import com.example.keycloakspringboot.dto.FilterRequest;
import com.example.keycloakspringboot.spec.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractRepositoryService<E, I> implements RepositoryService<E, I> {

    protected final BaseRepository<E, I> repository;

    protected final SpecificationBuilder<E> specificationBuilder;

    @Override
    @Transactional
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> find(I id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E update(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(I id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findList(FilterRequest filter) {
        Specification<E> specification = specificationBuilder.build(filter);
        return repository.findAll(specification);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<E> findPage(FilterRequest filter, Pageable pageable) {
        Specification<E> specification = specificationBuilder.build(filter);
        return repository.findAll(specification, pageable);
    }
}
