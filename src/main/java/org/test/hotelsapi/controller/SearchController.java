package org.test.hotelsapi.controller;

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
import org.test.hotelsapi.mapper.HotelMapper;
import org.test.hotelsapi.service.HotelService;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HotelShortResponse> search(@RequestParam Map<String, String> criteria) {
        return hotelMapper.toHotelShortResponseList(hotelService.findByCriteria(criteria));
    }
}
