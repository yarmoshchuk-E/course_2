package pro.skyjava.course2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.skyjava.course2.examinerservice.domain.Question;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount <= 0) return List.of();

        List<Question> all = new ArrayList<>(questionService.getAll());

        if (amount > all.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запрошено больше вопросов, чем доступно");
        }

        if (amount == all.size()) {
            return new ArrayList<>(all);
        }

        List<Question> result = new ArrayList<>();
        List<Question> copyQuestions = new ArrayList<>(all);

        Random rand = new Random();

        for (int i = 0; i < amount; i++) {
            int indx = rand.nextInt(copyQuestions.size());
            result.add(copyQuestions.get(indx));
            copyQuestions.remove(indx);
        }

        return result;
    }
}
