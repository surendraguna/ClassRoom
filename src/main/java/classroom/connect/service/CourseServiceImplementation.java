package classroom.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classroom.connect.entity.Course;
import classroom.connect.repository.CourseRepository;

@Service
public class CourseServiceImplementation implements CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

}
    
