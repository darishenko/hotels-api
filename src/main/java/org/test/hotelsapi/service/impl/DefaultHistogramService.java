package org.test.hotelsapi.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.hotelsapi.enums.HistogramParam;
import org.test.hotelsapi.repository.HotelRepository;
import org.test.hotelsapi.service.HistogramService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultHistogramService implements HistogramService {
    private static Map<HistogramParam, Supplier<List<Object[]>>> histogramRepositorySuppliers;

    private final HotelRepository hotelRepository;

    @PostConstruct
    private void init() {
        histogramRepositorySuppliers = Map.of(
                HistogramParam.brand, hotelRepository::countByBrands,
                HistogramParam.city, hotelRepository::countByCities,
                HistogramParam.country, hotelRepository::countByCountries,
                HistogramParam.amenities, hotelRepository::countByAmenities
        );
    }

    @Override
    public Map<String, Long> getCountByHistogramParam(HistogramParam histogramParam) {
        return histogramRepositorySuppliers.get(histogramParam).get().stream()
                .collect(Collectors.toMap(result -> (String) result[0], result -> (Long) result[1]));
    }

}
