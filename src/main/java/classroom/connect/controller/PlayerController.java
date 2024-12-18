package classroom.connect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import classroom.connect.entity.Video;
import classroom.connect.service.VideoService;

@RestController
@RequestMapping("classroom/player")
public class PlayerController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/{courseId}")
    public List<Video> getVideos(@PathVariable String courseId) {
        return videoService.getVideos(courseId);
    }
    
}
