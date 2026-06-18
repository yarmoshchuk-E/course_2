package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}