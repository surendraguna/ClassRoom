package classroom.connect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import classroom.connect.entity.Video;

@Service
public interface VideoService {

    List<Video> getVideos(String courseId);

    List<Video> getAllVideos();
    
}
