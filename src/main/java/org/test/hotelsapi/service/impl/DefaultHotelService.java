package org.test.hotelsapi.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.hotelsapi.entity.Amenity;
import org.test.hotelsapi.entity.Hotel;
import org.test.hotelsapi.enums.SearchCriteria;
import org.test.hotelsapi.exception.InvalidSearchCriteriaException;
import org.test.hotelsapi.exception.hotel.DuplicateHotelException;
import org.test.hotelsapi.exception.hotel.HotelNotFoundException;
import org.test.hotelsapi.repository.HotelRepository;
import org.test.hotelsapi.repository.specification.HotelSpecification;
import org.test.hotelsapi.service.AmenityService;
import org.test.hotelsapi.service.HotelService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultHotelService implements HotelService {
    private static final String COMMA = ",";

    private final AmenityService amenityService;
    private final HotelRepository hotelRepository;
    private final HotelSpecification hotelSpecification;

    @Override
    public Hotel get(long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> findByCriteria(Map<String, String> criteria) {
        Map<SearchCriteria, List<String>> searchCriteriaListMap = new HashMap<>();
        criteria.forEach((key, value) ->
        {
            try {
                searchCriteriaListMap.put(SearchCriteria.valueOf(key), splitCommaSeparatedValues(value));
            } catch (IllegalArgumentException e) {
                throw new InvalidSearchCriteriaException(key);
            }
        });

        return hotelRepository.findAll(hotelSpecification.getSpecification(searchCriteriaListMap));
    }

    @Override
    @Transactional
    public Hotel addAmenities(long hotelId, Set<String> amenities) {
        Hotel hotel = get(hotelId);
        Set<Amenity> hotelAmenities = amenities.stream()
                .map(amenityService::findOrCreate)
                .collect(Collectors.toSet());
        hotel.getAmenities().addAll(hotelAmenities);

        return hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    public Hotel createHotel(Hotel hotel) {
        if (hotelRepository.existsByNameIgnoreCase(hotel.getName())) {
            throw new DuplicateHotelException(hotel);
        }

        return hotelRepository.save(hotel);
    }

    private List<String> splitCommaSeparatedValues(String values) {
        if (Strings.isNotBlank(values)) {
            return Arrays.asList(values.split(COMMA));
        }

        return Collections.emptyList();
    }
}
