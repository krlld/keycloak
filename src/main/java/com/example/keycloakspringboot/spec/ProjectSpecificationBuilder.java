package com.example.keycloakspringboot.spec;

import com.example.keycloakspringboot.dto.FilterRequest;
import com.example.keycloakspringboot.dto.ProjectFilterDto;
import com.example.keycloakspringboot.entity.Project;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProjectSpecificationBuilder implements SpecificationBuilder<Project> {

    @Override
    public Specification<Project> build(FilterRequest filterRequest) {
        ProjectFilterDto projectFilterDto = (ProjectFilterDto) filterRequest;
        return (root, query, builder) -> {
            Predicate titlePredicate = builder.like(root.get("title"), "%" + projectFilterDto.getTitle() + "%");
            Predicate descriptionPredicate = builder.like(root.get("description"), "%" + projectFilterDto.getDescription() + "%");
            Predicate budgetPredicate = builder.lessThan(root.get("budget"), projectFilterDto.getBudget());
            return builder.and(titlePredicate, descriptionPredicate, budgetPredicate);
        };
    }
}
