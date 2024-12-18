package classroom.connect.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import classroom.connect.dto.User;
import classroom.connect.service.UserService;
import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    
    
    @GetMapping("/")
    public String enter(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Boolean is2faVerified = (Boolean) session.getAttribute("2faVerified");
            if (is2faVerified != null && is2faVerified) {
                return "classroom";
            }
            return "twofa"; 
        }
        return "index";
    }

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.register(user); 
            return ResponseEntity.ok(Map.of("message", "Registration successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

}
