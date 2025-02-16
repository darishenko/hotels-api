package org.test.hotelsapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private long id;
    @Schema(example = "DoubleTree by Hilton Minsk")
    private String name;
    @Schema(example = "Hilton")
    private String brand;
    @Schema(example = "The DoubleTree by Hilton Hotel Minsk offers ...")
    private String description;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    @Schema(example = "[\"Free parking\",\"Free WiFi\"]")
    private Set<String> amenities;
}
