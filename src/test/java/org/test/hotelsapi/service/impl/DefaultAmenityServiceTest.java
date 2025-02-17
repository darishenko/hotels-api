package org.test.hotelsapi.service.impl;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.test.hotelsapi.entity.Amenity;
import org.test.hotelsapi.repository.AmenityRepository;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAmenityServiceTest {
    private static final String EXISTING_AMENITY_NAME = "existingAmenity";
    private static final String NEW_AMENITY_NAME = "newAmenity";

    @InjectMocks
    private DefaultAmenityService amenityService;
    @Mock
    private AmenityRepository amenityRepository;

    @Test
    public void findOrCreate_existingAmenity_returnExistingAmenity() {
        final Amenity existingAmenity = new Amenity(EXISTING_AMENITY_NAME);
        when(amenityRepository.findByNameIgnoreCase(EXISTING_AMENITY_NAME)).thenReturn(Optional.of(existingAmenity));

        Amenity amenity = amenityService.findOrCreate(EXISTING_AMENITY_NAME);

        verify(amenityRepository).findByNameIgnoreCase(EXISTING_AMENITY_NAME);
        verify(amenityRepository, never()).save(any(Amenity.class));
        assertNotNull(amenity);
        assertEquals(existingAmenity, amenity);
    }

    @Test
    public void findOrCreate_newAmenity_returnNewAmenity() {
        final Amenity newAmenity = new Amenity(NEW_AMENITY_NAME);
        when(amenityRepository.findByNameIgnoreCase(NEW_AMENITY_NAME)).thenReturn(Optional.empty());
        when(amenityRepository.save(any(Amenity.class))).thenReturn(newAmenity);

        Amenity amenity = amenityService.findOrCreate(NEW_AMENITY_NAME);

        verify(amenityRepository).findByNameIgnoreCase(NEW_AMENITY_NAME);
        verify(amenityRepository).save(newAmenity);
        assertNotNull(amenity);
        assertEquals(newAmenity, amenity);
    }
}
