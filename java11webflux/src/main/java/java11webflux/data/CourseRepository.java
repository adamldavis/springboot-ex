package java11webflux.data;

import java11webflux.model.Course;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/** We could use Spring data here by making this an interface and extending
 * org.springframework.data.mongodb.repository.ReactiveMongoRepository for example.
 * This is just a dummy repo that uses a List.
 */
@Service
public class CourseRepository {

    final List<Course> courseList = new ArrayList<>();

    public Mono<Integer> count() {
        return Mono.fromCallable(() -> courseList.size());
    }

    public Mono<Course> save(Course course) {
        courseList.add(course);
        return Mono.just(course);
    }

    public Flux<Course> findAll() {
        return Flux.fromIterable(courseList);
    }

}
