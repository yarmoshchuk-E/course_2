package pro.skyjava.course2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount <= 0) {
            return List.of();
        }

        List<Question> allQuestions = new ArrayList<>(questionService.getAll());
        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Запрошено больше вопросов, чем доступно");
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            Question random = questionService.getRandomQuestion();
            if (random != null) { result.add(random);
            }
        } return new ArrayList<>(result);
    }
}
