package org.test.hotelsapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.hotelsapi.entity.Amenity;
import org.test.hotelsapi.repository.AmenityRepository;
import org.test.hotelsapi.service.AmenityService;

@Service
@RequiredArgsConstructor
public class DefaultAmenityService implements AmenityService {
    private final AmenityRepository amenityRepository;

    @Override
    @Transactional
    public Amenity findOrCreate(String name) {
        return amenityRepository.findByNameIgnoreCase(name).orElseGet(() -> amenityRepository.save(new Amenity(name)));
    }
}
