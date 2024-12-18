package classroom.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classroom.connect.entity.Video;
import classroom.connect.repository.VideoRepository;

@Service
public class VideoServiceImplementation implements VideoService {
    
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> getVideos(String courseId) {
        return videoRepository.findByCourse_Id(courseId);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
    
}
