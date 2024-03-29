package java11webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Test user takes to complete the course. Questions are picked randomly out of given list of questions. */
@Data
@AllArgsConstructor
public class Test {

    UUID id = UUID.randomUUID();

    /** List of quesions that make up the test. */
    final List<Question> questions = new ArrayList<>();

    int numberOfQuestions;

    public Test() {}

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }
}
