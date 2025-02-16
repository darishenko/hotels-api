package org.test.hotelsapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.test.hotelsapi.dto.response.HotelShortResponse;
import org.test.hotelsapi.exception.dto.ExceptionResponse;
import org.test.hotelsapi.mapper.HotelMapper;
import org.test.hotelsapi.service.HotelService;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    @Operation(summary = "Get hotels by search parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found hotels", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = HotelShortResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content =
            @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    public List<HotelShortResponse> search(@Parameter(description = "Search criteria as key-value pairs." +
            " Allowed keys: name, brand, city, country, amenities.",
            example = "{\"city\": \"Minsk,Mogilev\", \"brand\": \"Hilton\"}")
                                           @RequestParam Map<String, String> criteria) {
        return hotelMapper.toHotelShortResponseList(hotelService.findByCriteria(criteria));
    }
}
