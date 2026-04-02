package org.zin.com.phoneshopapi.controller;

import java.util.Map;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.zin.com.phoneshopapi.dto.request.LoginRequest;
import org.zin.com.phoneshopapi.dto.request.RegisterRequest;
import org.zin.com.phoneshopapi.dto.request.SendOtpRequest;
import org.zin.com.phoneshopapi.dto.request.VerifyOtpRequest;
import org.zin.com.phoneshopapi.dto.response.AuthResponse;
import org.zin.com.phoneshopapi.payload.ApiResponse;
import org.zin.com.phoneshopapi.payload.PageResponse;
//import org.zin.com.phoneshopapi.security.jwt.jwtUtil;

import org.zin.com.phoneshopapi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


//    private final AuthenticationManager authenticationManager;
//    private final jwtUtil jwtUtil;

    private final AuthService authService;



    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody SendOtpRequest request){
        return authService.sendOtp(request);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest
                                        request){
        return authService.verifyOtp(request);
    }

    @PostMapping("/login")
    public  ResponseEntity<ApiResponse<AuthResponse>>  login(@Valid  @RequestBody LoginRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("User Login Successfully",response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request){

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("User Register Successfully", authService.register(request)));


    }




//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//
//        String token = jwtUtil.generateToken(request.getEmail());
//        return ResponseEntity.ok(Map.of(
//                "token", token));
//    }
//    @PostMapping("/register")
//    public  ResponseEntity<ApiResponse<?>>register(@Valid @RequestBody RegisterRequest request) {
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User Register Successfully", authService.register(request)));
//    }

}
