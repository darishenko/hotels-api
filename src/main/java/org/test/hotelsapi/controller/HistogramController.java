package org.test.hotelsapi.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.test.hotelsapi.enums.HistogramParam;
import org.test.hotelsapi.service.HistogramService;

@RestController
@RequestMapping("histogram")
@RequiredArgsConstructor
public class HistogramController {
    private final HistogramService histogramService;

    @GetMapping("{param}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> getHotelsCountGroupBy(@PathVariable HistogramParam param) {
        return histogramService.getCountByHistogramParam(param);
    }
}
