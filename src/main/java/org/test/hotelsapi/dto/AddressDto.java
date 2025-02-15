package org.test.hotelsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    @NotBlank(message = "Country is required")
    private String country;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Street is required")
    private String street;
    @NotNull(message = "House number is required")
    private Integer houseNumber;
    @NotBlank(message = "Post code is required")
    private String postCode;
}
