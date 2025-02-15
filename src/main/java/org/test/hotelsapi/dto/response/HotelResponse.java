package org.test.hotelsapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.test.hotelsapi.dto.AddressDto;
import org.test.hotelsapi.dto.ArrivalTimeDto;
import org.test.hotelsapi.dto.ContactsDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class HotelResponse {
    private long id;
    private String name;
    private String brand;
    private String description;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    private Set<String> amenities;
}
