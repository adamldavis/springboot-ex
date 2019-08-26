package com.adamldavis.ecc.data;

import com.adamldavis.ecc.model.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course, UUID> {

}
