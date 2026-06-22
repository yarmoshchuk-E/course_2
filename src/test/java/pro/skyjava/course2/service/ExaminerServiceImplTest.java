package pro.skyjava.course2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.ExaminerServiceImpl;
import pro.skyjava.course2.examinerservice.service.QuestionService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_throwsException_WhenAmountExceedsAvailable() {
        List<Question> mockQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );
        when(questionService.getAll()).thenReturn(mockQuestions);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(5);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void getQuestions_throwsException_WhenListIsEmpty() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(5);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void getQuestions_returnsEmptyList_WhenAmountIsZero() {

        List<Question> result = examinerService.getQuestions(0);

        assertTrue(result.isEmpty());
    }

    @Test
    void getQuestions_returnsCorrectAmount() {

        List<Question> mockQuestions = List.of(
                new Question("вопрос_1", "ответ_1"),
                new Question("вопрос_2", "ответ_2"),
                new Question("вопрос_3", "ответ_3")
        );
        when(questionService.getAll()).thenReturn(mockQuestions);

        List<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
        assertTrue(mockQuestions.containsAll(result));

    }
}