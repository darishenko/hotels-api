package org.test.hotelsapi.mapper;

import java.util.List;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.test.hotelsapi.dto.request.HotelRequest;
import org.test.hotelsapi.dto.response.HotelResponse;
import org.test.hotelsapi.dto.response.HotelShortResponse;
import org.test.hotelsapi.entity.Address;
import org.test.hotelsapi.entity.Hotel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AmenityMapper.class)
public interface HotelMapper {
    String ADDRESS_INFO = "%d %s, %s, %s, %s";

    @Mapping(target = "phone", source = "contacts.phone")
    @Mapping(target = "address", qualifiedByName = "formatAddress")
    HotelShortResponse toHotelShortResponse(Hotel hotel);

    HotelResponse toHotelResponse(Hotel hotel);

    Hotel toHotel(HotelRequest hotelRequest);

    List<HotelShortResponse> toHotelShortResponseList(List<Hotel> hotel);

    @Named("formatAddress")
    default String formatAddress(Address address) {
        if (address != null) {
            return String.format(ADDRESS_INFO,
                    address.getHouseNumber(), address.getStreet(), address.getCity(), address.getPostCode(),
                    address.getCountry());
        }

        return Strings.EMPTY;
    }

}
