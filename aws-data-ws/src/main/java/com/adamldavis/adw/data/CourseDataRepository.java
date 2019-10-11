package com.adamldavis.adw.data;

import com.adamldavis.adw.model.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseDataRepository extends JpaRepository<CourseData, String> {

    /**
     * Finds all courses with name containing given string.
     * @param text String to search for.
     * @return List of Courses, will be empty if none found.
     */
    List<CourseData> findAllByNameContaining(String text);

    // example of using @Query and @Param
    @Query("select c from CourseData c where c.name in :names order by c.name")
    List<CourseData> findAllByNameIn(@Param("names") Collection<String> names);

}
