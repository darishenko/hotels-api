package org.test.hotelsapi.service;

import org.test.hotelsapi.entity.Amenity;

public interface AmenityService {
    Amenity findOrCreate(String name);
}
