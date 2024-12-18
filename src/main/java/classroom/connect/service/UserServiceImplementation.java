package classroom.connect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import classroom.connect.ExceptionHandle.UserAlreadyExistsException;
import classroom.connect.dto.Name;
import classroom.connect.dto.Password;
import classroom.connect.dto.User;
import classroom.connect.entity.Users;
import classroom.connect.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {   
        Users temp = userRepository.findByUsername(user.getUsername());
        if (temp != null) {
            throw new UserAlreadyExistsException("User already exists"); 
        }
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return user;
    }

    @Override
    public boolean updateUserDetails(Name user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users temp = userRepository.findByUsername(authentication.getName());
        temp.setFullname(user.getFullname());
        temp.setBio(user.getBio());
        return userRepository.save(temp) != null;
    }

    @Override
    public boolean updateUserPassword(Password user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users temp = userRepository.findByUsername(authentication.getName());
        if(passwordEncoder.matches(user.getNewPassword(), temp.getPassword())) return false;
        temp.setPassword(passwordEncoder.encode(user.getNewPassword()));
        return userRepository.save(temp) != null;
    }

    @Override
    public ResponseEntity<Name> getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users temp = userRepository.findByUsername(authentication.getName());
        Name user = new Name();
        user.setFullname(temp.getFullname());
        user.setBio(temp.getBio());
        return ResponseEntity.ok(user);
    }

}
