package classroom.connect.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import classroom.connect.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.otp = :otp, u.otpCreatedAt = :otpCreatedAt WHERE u.username = :username")
    void updateOtp(@Param("username") String username, @Param("otp") String otp, @Param("otpCreatedAt") LocalDateTime otpCreatedAt);
    
}
