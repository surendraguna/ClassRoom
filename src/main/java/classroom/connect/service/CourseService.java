package classroom.connect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import classroom.connect.entity.Course;

@Service
public interface CourseService {

    List<Course> getAllCourses();

}