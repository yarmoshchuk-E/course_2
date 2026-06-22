package pro.skyjava.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.*;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController

@RequestMapping("/exam")

public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/java/add")
    public void addQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        questionService.add(question, answer);
    }

    @DeleteMapping("/java/remove")
    public boolean removeQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping("/java/")
    public Collection<Question> getAllQuestions() {
        return questionService.getAll();
    }
}