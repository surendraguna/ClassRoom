package classroom.connect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.connect.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    List<Video> findByCourse_Id(String courseId);    

}
