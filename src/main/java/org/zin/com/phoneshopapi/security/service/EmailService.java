package org.zin.com.phoneshopapi.security.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;






    public void sentOtp(String email, String otp) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Email Verification");
//        message.setText("Your OTP code is: " + otp);
//        mailSender.send(message);




        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Email Verification");

        String htmlContent = """
            <html>
            <body style="font-family: Arial; background:#f4f4f4; padding:20px;">
                <div style="max-width:500px; margin:auto; background:white; padding:20px; border-radius:10px;">
                    <h2 style="color:#333;">Email Verification</h2>
                    <p>Your OTP code is:</p>
                    <h1 style="color:#2E86C1;">%s</h1>
                    <p>This code will expire in 5 minutes.</p>
                </div>
            </body>
            </html>
            """.formatted(otp);

        helper.setText(htmlContent, true); // true = HTML

        mailSender.send(message);

    }
}
