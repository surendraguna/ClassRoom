package classroom.connect.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import classroom.connect.entity.Users;
import classroom.connect.repository.UserRepository;

@Service
public class EmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mail;

    @Value("${spring.mail.username}")
    private String sender;
    
    public void sendEmail(String email) {
        String otp = generateOtp();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(email);
        msg.setSubject("OTP for Classroom Access");
        msg.setText("Hello,\n\n" +
                    "You requested access to the classroom system. Your One-Time Password (OTP) is:\n\n" +
                    otp + "\n\n" +
                    "This OTP is valid for 5 minutes and can only be used once. Please enter it in the system to proceed.\n\n" +
                    "If you did not request this OTP, please disregard this message.\n\n" +
                    "Best regards,\n" +
                    "Classroom Team");
        mail.send(msg);                  
        userRepository.updateOtp(email, otp, LocalDateTime.now());
    }

    private String generateOtp() {
        Random rand = new Random();
        int otp = rand.nextInt(100000, 999999);
        return String.valueOf(otp);
    }

    public boolean verifyOtp(String otp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users temp = userRepository.findByUsername(authentication.getName());
        if (temp.getOtp().equals(otp) && temp.getOtpCreatedAt().isAfter(LocalDateTime.now().minusMinutes(5))) {
            temp.setOtp(null);
            userRepository.save(temp);
            return true;
        }
        return false;
    }
    

}
