package org.test.hotelsapi.exception.hotel;

public class HotelNotFoundException extends RuntimeException {
    private static final String HOTEL_WITH_ID_NOT_FOUND = "Hotel with ID %s not found.";

    public HotelNotFoundException(long id) {
        super(String.format(HOTEL_WITH_ID_NOT_FOUND, id));
    }
}
