package org.test.hotelsapi.exception.hotel;

import org.test.hotelsapi.entity.Hotel;

public class DuplicateHotelException extends RuntimeException {
    private static final String DUPLICATE_HOTEL_NAME = "Hotel with name '%s' already exists";

    public DuplicateHotelException(Hotel hotel) {
        super(String.format(DUPLICATE_HOTEL_NAME, hotel.getName()));
    }
}
