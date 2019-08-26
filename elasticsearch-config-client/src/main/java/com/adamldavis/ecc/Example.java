package com.adamldavis.ecc;

import com.adamldavis.ecc.data.CourseRepository;
import com.adamldavis.ecc.model.Course;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class Example {

    @Autowired
    CourseRepository courseRepository;

    static final Logger logger = LoggerFactory.getLogger(Example.class);

    static final String[] adjectives = {"Advanced", "Beginning", "Super", "The Essential", "Learning"};

    static final String[] languages = {"Java", "Groovy", "Go", "Python", "Ruby", "Lisp", "JavaScript", "TypeScript"};

    static final String[] suffixes = {"101", "for Beginners", "for Professionals", "for Data Science", ""};

    static final int NUM_COURSES = 100;

    @PostConstruct
    public void run() {
        courseRepository.deleteAll();
        addRandomCourses();
        findCoursesAndPrint("java");
        findCoursesAndPrint("javascrip");
    }

    private void addRandomCourses() {
        Random rnd = ThreadLocalRandom.current();
        for (int i =0; i < NUM_COURSES; i++) {
            String prefix = adjectives[rnd.nextInt(adjectives.length)];
            String lang = languages[rnd.nextInt(languages.length)];
            String suffix = suffixes[rnd.nextInt(suffixes.length)];
            long price = rnd.nextInt(9999) + 2000;
            Course course = Course.create(prefix + " " + lang + " " + suffix, price);
            courseRepository.save(course);
        }
    }

    private void findCoursesAndPrint(String searchString) {
        FuzzyQueryBuilder queryBuilder = new FuzzyQueryBuilder("name", searchString);

        courseRepository.search(queryBuilder).forEach(course ->
            logger.info(searchString + ": Found course:" + course)
        );
    }

}
