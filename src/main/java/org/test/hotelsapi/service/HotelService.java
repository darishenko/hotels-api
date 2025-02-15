package org.test.hotelsapi.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.test.hotelsapi.entity.Hotel;

public interface HotelService {
    Hotel get(long id);

    List<Hotel> getAll();

    List<Hotel> findByCriteria(Map<String, String> criteria);

    Hotel addAmenities(long hotelId, Set<String> amenities);

    Hotel createHotel(Hotel hotel);
}
