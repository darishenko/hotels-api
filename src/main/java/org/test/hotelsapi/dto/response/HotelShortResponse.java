package org.test.hotelsapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class HotelShortResponse {
    @Schema(example = "1")
    private long id;
    @Schema(example = "DoubleTree by Hilton Minsk")
    private String name;
    @Schema(example = "The DoubleTree by Hilton Hotel Minsk offers ...")
    private String description;
    @Schema(example = "\"9 Pobediteley Avenue, Minsk, 220004, Belarus\"", type = "string")
    private String address;
    @Schema(example = "+375 17 309-80-00")
    private String phone;
}
