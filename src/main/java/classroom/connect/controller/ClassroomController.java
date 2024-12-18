package classroom.connect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import classroom.connect.dto.CourseId;

@Controller
@RequestMapping("classroom")
@SessionAttributes("courseId")
public class ClassroomController {
    
    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/player")
    public String player(@RequestBody CourseId courseId, Model model) {
        model.addAttribute("courseId", courseId.getCourseId()); 
        return "player";
    }

    @GetMapping("/player")
    public String viewPlayer(@ModelAttribute("courseId") String courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "player"; 
    }
}
