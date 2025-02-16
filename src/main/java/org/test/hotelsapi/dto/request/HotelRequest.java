package org.test.hotelsapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.test.hotelsapi.dto.AddressDto;
import org.test.hotelsapi.dto.ArrivalTimeDto;
import org.test.hotelsapi.dto.ContactsDto;

@Getter
@Setter
public class HotelRequest {
    @Schema(example = "DoubleTree by Hilton Minsk")
    @NotBlank(message = "Name is required")
    private String name;
    @Schema(example = "Hilton")
    @NotBlank(message = "Brand is required")
    private String brand;
    @Schema(example = "The DoubleTree by Hilton Hotel Minsk offers ...")
    private String description;
    @Valid
    @NotNull
    private AddressDto address;
    @Valid
    @NotNull
    private ContactsDto contacts;
    @Valid
    @NotNull
    private ArrivalTimeDto arrivalTime;
}
