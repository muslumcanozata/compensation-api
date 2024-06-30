package com.api.compesations.service;

import com.api.compesations.domain.entity.Compensation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CompensationSpecification {
    public static Specification<Compensation> getSpecifications(Map<String, String> filters) {
        List<Specification<Compensation>> specifications = new ArrayList<>();

        findSpecifications(filters, specifications);

        Specification<Compensation> result = specifications.get(0);

        for (int i = 1; i < specifications.size(); i++) {
            result = Specification.where(result).and(specifications.get(i));
        }

        return result;
    }

    private static void findSpecifications(Map<String, String> filters, List<Specification<Compensation>> specifications) {
        filters.forEach((key, value) -> {
            if (key.endsWith("[gte]")) {
                String field = key.substring(0, key.length() - 5);
                specifications.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get(field), value));
            } else if (key.endsWith("[lte]")) {
                String field = key.substring(0, key.length() - 5);
                specifications.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get(field), value));
            } else {
                specifications.add((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(key), value));
            }
        });
    }

}
