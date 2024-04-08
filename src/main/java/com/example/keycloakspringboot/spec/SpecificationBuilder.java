package com.example.keycloakspringboot.spec;

import com.example.keycloakspringboot.dto.FilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<E> {

    Specification<E> build(FilterRequest filterRequest);
}
