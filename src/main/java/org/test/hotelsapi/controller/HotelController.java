package org.test.hotelsapi.controller;

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
import org.test.hotelsapi.mapper.HotelMapper;
import org.test.hotelsapi.service.HotelService;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HotelShortResponse> getHotels() {
        return hotelMapper.toHotelShortResponseList(hotelService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelShortResponse createHotel(@Valid @RequestBody HotelRequest hotelRequest) {
        return hotelMapper.toHotelShortResponse(hotelService.createHotel(hotelMapper.toHotel(hotelRequest)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HotelResponse getHotel(@PathVariable long id) {
        return hotelMapper.toHotelResponse(hotelService.get(id));
    }

    @PostMapping("/{id}/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAmenitiesToHotel(@PathVariable long id, @RequestBody Set<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }

}
