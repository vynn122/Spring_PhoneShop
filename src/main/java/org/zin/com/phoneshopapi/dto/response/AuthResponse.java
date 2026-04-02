package org.zin.com.phoneshopapi.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private final String token;

    private final UserResponse user;
}
