package classroom.connect.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(255)")
    private String fullname = "Full Name";
    
    @Column(columnDefinition = "VARCHAR(255)")
    private String bio = "Computer Science student passionate about web development.";

    @Column(columnDefinition = "VARCHAR(255)")
    private String otp; 

    @Column(name = "otp_created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime otpCreatedAt;
}
