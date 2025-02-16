package org.test.hotelsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    @Schema(example = "Belarus")
    @NotBlank(message = "Country is required")
    private String country;
    @Schema(example = "Minsk")
    @NotBlank(message = "City is required")
    private String city;
    @Schema(example = "Pobediteley Avenue")
    @NotBlank(message = "Street is required")
    private String street;
    @Schema(example = "9")
    @NotNull(message = "House number is required")
    private Integer houseNumber;
    @Schema(example = "\"220004\"", type = "string")
    @NotBlank(message = "Post code is required")
    private String postCode;
}
