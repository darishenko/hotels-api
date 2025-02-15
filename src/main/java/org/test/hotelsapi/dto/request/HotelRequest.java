package org.test.hotelsapi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.test.hotelsapi.dto.AddressDto;
import org.test.hotelsapi.dto.ArrivalTimeDto;
import org.test.hotelsapi.dto.ContactsDto;

@Getter
@Setter
public class HotelRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Brand is required")
    private String brand;
    private String description;
    @Valid
    private AddressDto address;
    @Valid
    private ContactsDto contacts;
    @Valid
    private ArrivalTimeDto arrivalTime;
}
