package org.test.hotelsapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactsDto {
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Email is required")
    private String email;
}
