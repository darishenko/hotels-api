package org.test.hotelsapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrivalTimeDto {
    @Schema(example = "12:00", format = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Check in is required")
    private LocalTime checkIn;
    @Schema(example = "14:00", format = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;
}
