package com.adamldavis.adw.ws;


import com.adamldavis.adw.data.CourseDataRepository;
import com.adamldavis.adw.model.CourseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/courses",
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CourseWebService {

    public static class Result {
        final String status;
        public Result(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }
    }
    
    @Autowired
    CourseDataRepository courseDataRepository;

    @GetMapping
    public PageResult<CourseData> getCourses(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "25") int size,
                                       @RequestParam(required = false) String nameQuery) {
        if (nameQuery != null) {
            final List<CourseData> list = courseDataRepository.findAllByNameContaining(nameQuery);
            return new PageResult<>(list, list.size(), "");
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name"));
        final Page<CourseData> result = courseDataRepository.findAll(pageRequest);

        return new PageResult<>(result.getContent(), result.getNumberOfElements(),
                "/courses?page=" + (page+1) + "&size=" + size);
    }
    
    @PostMapping
    public Result saveCourse(@RequestBody CourseData courseData) {
        courseDataRepository.save(courseData);
        
        return new Result("saved");
    }
    
    @PutMapping("/{id}")
    public Result updateCourse(@PathParam("id") String id, @RequestBody CourseData courseData) {
        CourseData existing = courseDataRepository.getOne(id);
        if (existing == null) {
            throw new IllegalArgumentException(id + " not found");
        }
        existing.setName(courseData.getName());
        existing.setPrice(courseData.getPrice());
        courseDataRepository.save(existing);

        return new Result("saved");
    }

    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathParam("id") String id) {
        courseDataRepository.deleteById(id);

        return new Result("deleted");
    }

    // handles response when IllegalArgumentException is thrown from any method
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleEx(IllegalArgumentException ex) {
        return new Result(ex.getMessage());
    }
    
}
