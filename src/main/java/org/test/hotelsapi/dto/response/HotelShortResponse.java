package org.test.hotelsapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class HotelShortResponse {
    private long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
