package org.zin.com.phoneshopapi.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.exception.NotFoundException;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

//    private final StringRedisTemplate stringRedisTemplate;
    private final StringRedisTemplate redisTemplate;


    public String generateOtp(){

        return String.valueOf(new Random().nextInt(900000)+100000);
    }

    public void saveOtp(String email,String otp){

        redisTemplate.opsForValue().set(
                "otp:"+email,
                otp,
                Duration.ofMinutes(5)
        );
    }

    public boolean verifyOtp(String email,String otp){

        String storedOtp = redisTemplate.opsForValue().get("otp:"+email);

        if(storedOtp == null){
            throw new RuntimeException("OTP expired");
        }

        return storedOtp.equals(otp);
    }


}
