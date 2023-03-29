package hu.bme.aut.saturn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/saturn")
    public String getDummy() {
        return "HELLO";
    }

    @GetMapping("/courses")
    public Course getCourses(){
        Course course = new Course();
        course.setName("asdasdasd");

        return course;
    }

}
