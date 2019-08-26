package java11webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Course {

    UUID id = UUID.randomUUID();

    String name;
    long price = 2000; // $20.00 is default price

    final List<Segment> segments = new ArrayList<>();
    final List<Test> tests = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public void setSegments(List<Segment> segments) {
        this.segments.clear();
        this.segments.addAll(segments);
    }
    
    public void setTests(List<Test> tests) {
        this.tests.clear();
        this.tests.addAll(tests);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
