package classroom.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.connect.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
        
} 
