package java11webflux.control;

import java11webflux.data.CourseRepository;
import java11webflux.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CourseController {

    final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/api/courses")
    public Flux<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/api/courses")
    public Mono<Course> postCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }
}
