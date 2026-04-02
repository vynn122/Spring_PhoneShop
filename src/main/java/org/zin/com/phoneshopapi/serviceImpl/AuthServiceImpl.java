package org.zin.com.phoneshopapi.serviceImpl;

import jakarta.mail.MessagingException;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.dto.request.LoginRequest;
import org.zin.com.phoneshopapi.dto.request.RegisterRequest;
import org.zin.com.phoneshopapi.dto.request.SendOtpRequest;
import org.zin.com.phoneshopapi.dto.request.VerifyOtpRequest;
import org.zin.com.phoneshopapi.dto.response.AuthResponse;
import org.zin.com.phoneshopapi.dto.response.UserResponse;
import org.zin.com.phoneshopapi.entity.Role;
import org.zin.com.phoneshopapi.entity.User;
import org.zin.com.phoneshopapi.exception.EmailAlreadyExistException;
import org.zin.com.phoneshopapi.mapper.UserMapper;
import org.zin.com.phoneshopapi.repository.UserRepository;
import org.zin.com.phoneshopapi.security.service.EmailService;
import org.zin.com.phoneshopapi.security.jwt.JwtService;
import org.zin.com.phoneshopapi.security.service.OtpService;
import org.zin.com.phoneshopapi.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final StringRedisTemplate redisTemplate;



    private final AuthenticationManager  authenticationManager;
    private final UserMapper userMapper;

    @Override
    public String sendOtp(SendOtpRequest request) throws MessagingException {
        // Check if email already registered
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("Email already registered");
        }
        String email = request.getEmail();

        String otp = otpService.generateOtp();

        otpService.saveOtp(email, otp);

        emailService.sentOtp(email, otp);
        return "OTP sent";
    }

    @Override
    public String verifyOtp(VerifyOtpRequest request) {
        boolean valid = otpService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        if(!valid){
            throw new RuntimeException("Invalid OTP");
        }

        String verificationToken =
                UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(
                "verify:"+request.getEmail(),
                verificationToken,
                Duration.ofMinutes(10)
        );

        return verificationToken;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("Email already exists");
        }
        String storedToken = redisTemplate.opsForValue()
                .get("verify:"+request.getEmail());

        if(storedToken == null ||
                !storedToken.equals(request.getVerificationToken())){
            throw new RuntimeException("Email not verified");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        String token = jwtService.generateToken(request.getEmail());
        UserResponse userResponse = mapper.toDto(user);
        return new AuthResponse(token, userResponse);
    }




    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        UserResponse userResponse = userMapper.toDto(user);

        return new AuthResponse(token, userResponse);
    }




//    @Override
//    public UserResponse register(RegisterRequest request) {
//
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new EmailAlreadyExistException("Email already exists");
//        }
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.ROLE_USER);
//        userRepository.save(user);
//        return mapper.toDTO(user);
//    }



}
