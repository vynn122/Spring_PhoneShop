package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.LoginRequest;
import org.zin.com.phoneshopapi.dto.request.RegisterRequest;
import org.zin.com.phoneshopapi.dto.request.SendOtpRequest;
import org.zin.com.phoneshopapi.dto.request.VerifyOtpRequest;
import org.zin.com.phoneshopapi.dto.response.AuthResponse;
import org.zin.com.phoneshopapi.dto.response.UserResponse;
import org.zin.com.phoneshopapi.entity.User;
import org.zin.com.phoneshopapi.payload.PageResponse;

public interface AuthService {
//    UserResponse register(RegisterRequest request);
//
//    String login(String email, String password);


//    String sendOtp(String email);


    String sendOtp(SendOtpRequest request);

    String verifyOtp(VerifyOtpRequest request);

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
