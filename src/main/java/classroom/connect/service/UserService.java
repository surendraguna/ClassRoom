package classroom.connect.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import classroom.connect.dto.Name;
import classroom.connect.dto.Password;
import classroom.connect.dto.User;

@Service
public interface UserService {

    User register(User user);

    boolean updateUserDetails(Name user);

    boolean updateUserPassword(Password user);

    ResponseEntity<Name> getCurrentUserDetails();
}
