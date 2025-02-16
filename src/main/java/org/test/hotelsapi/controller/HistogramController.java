package org.test.hotelsapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get number of hotels grouped by each value of histogram parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found hotels count by parameter values",
                    content = {@Content(schema = @Schema(example = "{\"Minsk\": 1,\"Moskow\": 2,\"Mogilev\": 0}"))})
    })
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> getHotelsCountGroupBy(
            @Parameter(description = "Parameter for which values number of hotels is calculated")
            @PathVariable HistogramParam param) {
        return histogramService.getCountByHistogramParam(param);
    }
}
