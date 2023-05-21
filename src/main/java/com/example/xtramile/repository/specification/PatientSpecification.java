package com.example.xtramile.repository.specification;

import com.example.xtramile.dto.request.FilterPatientList;
import com.example.xtramile.entity.PatientEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientSpecification {

    private static final String PERCENT_SIGN = "%";
    public static Specification<PatientEntity> filter(FilterPatientList request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(request.getPid())
                    .filter(StringUtils::isNotBlank)
                    .map(pid -> PERCENT_SIGN + StringUtils.lowerCase(pid) + PERCENT_SIGN)
                    .map(pid -> criteriaBuilder.like(criteriaBuilder.lower(root.get("pid")), pid))
                    .ifPresent(predicates::add);
            Optional.ofNullable(request.getFirstName())
                    .filter(StringUtils::isNotBlank)
                    .map(firstName -> PERCENT_SIGN + StringUtils.lowerCase(firstName) + PERCENT_SIGN)
                    .map(firstName -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), firstName))
                    .ifPresent(predicates::add);
            Optional.ofNullable(request.getFirstName())
                    .filter(StringUtils::isNotBlank)
                    .map(lastName -> PERCENT_SIGN + StringUtils.lowerCase(lastName) + PERCENT_SIGN)
                    .map(lastName -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), lastName))
                    .ifPresent(predicates::add);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
