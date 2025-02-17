package org.test.hotelsapi.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import org.test.hotelsapi.entity.Amenity;
import org.test.hotelsapi.entity.Hotel;
import org.test.hotelsapi.exception.InvalidSearchCriteriaException;
import org.test.hotelsapi.exception.hotel.DuplicateHotelException;
import org.test.hotelsapi.exception.hotel.HotelNotFoundException;
import org.test.hotelsapi.repository.HotelRepository;
import org.test.hotelsapi.repository.specification.HotelSpecification;
import org.test.hotelsapi.service.AmenityService;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultHotelServiceTest {
    private static final long HOTEL_ID = 1L;
    private static final String EXISTING_HOTEL_NAME = "Existing hotel name";
    private static final Set<Amenity> EXISTING_AMENITIES = new HashSet<>();
    private static final Hotel EXISTING_HOTEL = new Hotel();

    @InjectMocks
    private DefaultHotelService hotelService;
    @Mock
    private AmenityService amenityService;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private HotelSpecification hotelSpecification;

    @Before
    public void setUp() {
        EXISTING_HOTEL.setId(HOTEL_ID);
        EXISTING_HOTEL.setName(EXISTING_HOTEL_NAME);
        EXISTING_AMENITIES.add(new Amenity("Amenity 1"));
        EXISTING_AMENITIES.add(new Amenity("Amenity 2"));
        EXISTING_HOTEL.setAmenities(EXISTING_AMENITIES);
    }

    @Test
    public void get_existingHotelId_returnHotel() {
        when(hotelRepository.findById(any())).thenReturn(Optional.of(EXISTING_HOTEL));

        Hotel hotel = hotelService.get(EXISTING_HOTEL.getId());

        verify(hotelRepository).findById(EXISTING_HOTEL.getId());
        assertEquals(EXISTING_HOTEL, hotel);
    }

    @Test(expected = HotelNotFoundException.class)
    public void get_notExistingHotelId_throwHotelNotFoundException() {
        when(hotelRepository.findById(any())).thenReturn(Optional.empty());

        hotelService.get(HOTEL_ID);

        verify(hotelRepository).findById(HOTEL_ID);
    }

    @Test
    public void getAll_returnHotels() {
        List<Hotel> expectedHotels = Collections.singletonList(EXISTING_HOTEL);
        when(hotelRepository.findAll()).thenReturn(expectedHotels);

        List<Hotel> hotels = hotelService.getAll();

        verify(hotelRepository).findAll();
        assertEquals(expectedHotels, hotels);
    }

    @Test
    public void addAmenities_newAmenity_addNewAmenityToHotel() {
        String newAmenityName = "New amenity 1";
        Amenity newAmenity = new Amenity(newAmenityName);
        Set<String> newAmenitiesNames = Collections.singleton(newAmenityName);
        when(hotelRepository.findById(any())).thenReturn(Optional.of(EXISTING_HOTEL));
        when(amenityService.findOrCreate(newAmenityName)).thenReturn(newAmenity);
        when(hotelRepository.save(any(Hotel.class))).thenReturn(EXISTING_HOTEL);

        Set<Amenity> expectedAmenities = Sets.newHashSet(EXISTING_AMENITIES);
        expectedAmenities.add(newAmenity);
        Hotel hotel = hotelService.addAmenities(EXISTING_HOTEL.getId(), newAmenitiesNames);

        verify(amenityService).findOrCreate(newAmenityName);
        assertEquals(expectedAmenities.size(), hotel.getAmenities().size());
        assertArrayEquals(expectedAmenities.toArray(), hotel.getAmenities().toArray());
    }

    @Test
    public void createHotel_newHotelName_createHotel() {
        when(hotelRepository.existsByNameIgnoreCase(EXISTING_HOTEL_NAME)).thenReturn(false);
        when(hotelRepository.save(any(Hotel.class))).thenReturn(EXISTING_HOTEL);

        Hotel hotel = hotelService.createHotel(EXISTING_HOTEL);

        verify(hotelRepository).existsByNameIgnoreCase(EXISTING_HOTEL_NAME);
        verify(hotelRepository).save(any(Hotel.class));
        assertNotNull(hotel);
    }

    @Test(expected = DuplicateHotelException.class)
    public void createHotel_duplicateName_throwDuplicateHotelException() {
        when(hotelRepository.existsByNameIgnoreCase(EXISTING_HOTEL_NAME)).thenReturn(true);

        hotelService.createHotel(EXISTING_HOTEL);

        verify(hotelRepository).existsByNameIgnoreCase(EXISTING_HOTEL_NAME);
        verify(hotelRepository, never()).save(any(Hotel.class));
    }

    @Test
    public void findByCriteria_severalCriteria_findHotelsByCriteria() {
        List<Hotel> expectedHotels = List.of(new Hotel(), new Hotel());
        Map<String, String> criteriaMap = Map.of("city", "city 1,city 2", "brand", "brand 1");
        Specification<Hotel> specification = mock(Specification.class);
        when(hotelSpecification.getSpecification(anyMap())).thenReturn(specification);
        when(hotelRepository.findAll(specification)).thenReturn(expectedHotels);

        List<Hotel> hotels = hotelService.findByCriteria(criteriaMap);

        assertEquals(expectedHotels.size(), hotels.size());
        assertArrayEquals(expectedHotels.toArray(), hotels.toArray());
    }

    @Test(expected = InvalidSearchCriteriaException.class)
    public void findByCriteria_invalidCriteria_throwInvalidSearchCriteriaException() {
        Map<String, String> criteriaMap = Map.of("criteria", "criteriaValue1,criteriaValue2");

        hotelService.findByCriteria(criteriaMap);
    }
}
