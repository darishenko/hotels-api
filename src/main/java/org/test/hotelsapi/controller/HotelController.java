package org.test.hotelsapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.test.hotelsapi.dto.request.HotelRequest;
import org.test.hotelsapi.dto.response.HotelResponse;
import org.test.hotelsapi.dto.response.HotelShortResponse;
import org.test.hotelsapi.exception.dto.ExceptionResponse;
import org.test.hotelsapi.mapper.HotelMapper;
import org.test.hotelsapi.service.HotelService;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    @Operation(summary = "Get hotels brief information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels information received", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = HotelShortResponse.class)))),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<HotelShortResponse> getHotels() {
        return hotelMapper.toHotelShortResponseList(hotelService.getAll());
    }

    @PostMapping
    @Operation(summary = "Create hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel created", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = HotelShortResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Hotel not created", content =
            @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public HotelShortResponse createHotel(@Valid @RequestBody HotelRequest hotelRequest) {
        return hotelMapper.toHotelShortResponse(hotelService.createHotel(hotelMapper.toHotel(hotelRequest)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hotel information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel information found", content =
            @Content(schema = @Schema(implementation = HotelResponse.class))),
            @ApiResponse(responseCode = "404", description = "Hotel not found", content =
            @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @ResponseStatus(HttpStatus.OK)
    public HotelResponse getHotel(@Parameter(description = "Hotel id") @PathVariable long id) {
        return hotelMapper.toHotelResponse(hotelService.get(id));
    }

    @PostMapping("/{id}/amenities")
    @Operation(summary = "Add amenities to hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Amenities added to hotel"),
            @ApiResponse(responseCode = "404", description = "Hotel not found", content =
            @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void addAmenitiesToHotel(@Parameter(description = "Hotel id") @PathVariable long id,
                                    @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                            description = "Array of amenities",
                                            content = @Content(array = @ArraySchema(schema =
                                            @Schema(implementation = String.class))))
                                    @RequestBody Set<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }

}
