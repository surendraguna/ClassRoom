package classroom.connect.controller;

import org.springframework.web.bind.annotation.RestController;

import classroom.connect.dto.OTP;
import classroom.connect.service.EmailService;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OtpVerification {

    @Autowired
    private EmailService emailService;

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OTP otp, HttpSession session) {
        boolean isValid = emailService.verifyOtp(otp.getOtp());
        if (isValid) {
            session.setAttribute("2faVerified", true);
            return ResponseEntity.ok(Map.of("success", "OTP verified successfully"));
        } 
        return ResponseEntity.status(400).body(Map.of("error", "OTP verification failed"));
    }
    
}

