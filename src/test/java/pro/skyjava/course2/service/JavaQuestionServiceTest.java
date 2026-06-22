package pro.skyjava.course2.service;

import org.junit.jupiter.api.Test;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    void add_lines_to_an_object() {

        String question = "вопрос";
        String answer = "ответ";

        service.add(question, answer);

        assertEquals(1, service.getAll().size());
    }

    @Test
    void add_unique_questions_only() {
        Question q1 = new Question("вопрос", "ответ");
        Question duplicate = new Question("вопрос", "ответ");

        service.add(q1);
        service.add(duplicate);

        assertEquals(1, service.getAll().size());
        assertTrue(service.getAll().contains(q1));
    }


    @Test
    void getAll_returnsAllStoredQuestions() {

        List<Question> expected = List.of(
                new Question("вопрос_1", "ответ_1"),
                new Question("вопрос_2", "ответ_2")
        );

        expected.forEach(service::add);

        Collection<Question> result = service.getAll();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }


    @Test
    void getRandomQuestion_returnsOneOfTheExisting() {

        List<Question> mockList = List.of(
                new Question("вопрос_1", "ответ_1"),
                new Question("вопрос_2", "ответ_2"),
                new Question("вопрос_3", "ответ_3")
        );

        for (Question q : mockList) {
            service.add(q);
        }

        Question random = service.getRandomQuestion();

        assertNotNull(random);
        assertTrue(mockList.contains(random));
    }

    @Test
    void removeQuestion_fromStorage() {

        Question toRemove = new Question("вопрос_1", "ответ_1");
        service.add(toRemove);
        service.add(new Question("вопрос_2", "ответ_2"));

        assertEquals(2, service.getAll().size());

        service.remove(toRemove);

        assertEquals(1, service.getAll().size());
        assertFalse(service.getAll().contains(toRemove));
    }

    @Test
    void removeQuestion_FromEmpty() {

        Question q = new Question("вопрос", "ответ");

        assertFalse(service.remove(q));
        assertEquals(0, service.getAll().size());
    }
}