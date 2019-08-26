package com.adamldavis.adw.data;

import com.adamldavis.adw.model.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDataRepository extends JpaRepository<CourseData, String> {

}
