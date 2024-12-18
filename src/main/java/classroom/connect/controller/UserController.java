package classroom.connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import classroom.connect.dto.Name;
import classroom.connect.dto.Password;
import classroom.connect.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/details")
    public ResponseEntity<Name> getUserDetails() {
        return userService.getCurrentUserDetails();
    }

    @PutMapping("/name")
    public boolean updateUserDetails(@RequestBody Name user) {
        return userService.updateUserDetails(user);
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody Password user) {
        boolean isUpdated = userService.updateUserPassword(user);
        if (isUpdated) {
            return ResponseEntity.ok("Password updated successfully");
        }
        return ResponseEntity.badRequest().body("Password update failed");
    }
}
