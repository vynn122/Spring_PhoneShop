package org.zin.com.phoneshopapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SendOtpRequest {

    @Email
    @NotBlank(message = "Email is required")
    private String email;
}
