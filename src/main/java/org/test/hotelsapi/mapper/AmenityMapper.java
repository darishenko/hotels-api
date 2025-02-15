package org.test.hotelsapi.mapper;

import java.util.Set;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.test.hotelsapi.entity.Amenity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AmenityMapper {
    @IterableMapping(qualifiedByName = "getAmenityName")
    Set<String> toStringSet(Set<Amenity> amenities);

    @Named("getAmenityName")
    default String getAmenityName(Amenity amenity) {
        return amenity != null ? amenity.getName() : null;
    }
}
