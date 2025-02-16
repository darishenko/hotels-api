package org.test.hotelsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactsDto {
    @Schema(example = "+375 17 309-80-00")
    @NotBlank(message = "Phone is required")
    private String phone;
    @Schema(example = "doubletreeminsk.info@hilton.com")
    @NotBlank(message = "Email is required")
    private String email;
}
