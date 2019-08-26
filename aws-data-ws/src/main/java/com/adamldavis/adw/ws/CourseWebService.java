package com.adamldavis.adw.ws;


import com.adamldavis.adw.data.CourseDataRepository;
import com.adamldavis.adw.model.CourseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CourseWebService {

    @Autowired
    CourseDataRepository courseDataRepository;

    @GetMapping(path = "/courses", produces = "application/json")
    public Page<CourseData> getCourses(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "25") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name"));

        return courseDataRepository.findAll(pageRequest);
    }

}
