package org.test.hotelsapi.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.test.hotelsapi.entity.Hotel;
import org.test.hotelsapi.enums.SearchCriteria;

@Component
public class HotelSpecification {
    private static final String ADDRESS = "address";
    private static final String NAME = "name";

    public Specification<Hotel> getSpecification(Map<SearchCriteria, List<String>> criteria) {
        return (Root<Hotel> hotel, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();
            for (Map.Entry<SearchCriteria, List<String>> entry : criteria.entrySet()) {
                SearchCriteria key = entry.getKey();
                List<String> values = entry.getValue();
                if (!CollectionUtils.isEmpty(values)) {
                    Path<String> field = switch (key) {
                        case name, brand -> hotel.get(key.name());
                        case city, country -> hotel.get(ADDRESS).get(key.name());
                        case amenities -> hotel.join(key.name()).get(NAME);
                    };

                    predicate = addInQuery(predicate, cb, field, values);
                }
            }

            return predicate;
        };
    }

    private Predicate addInQuery(Predicate predicate, CriteriaBuilder cb, Path<String> field,
                                 List<String> values) {
        List<String> lowerValues = values.stream()
                .map(String::toLowerCase)
                .toList();

        return cb.and(predicate, cb.lower(field).in(lowerValues));
    }
}
