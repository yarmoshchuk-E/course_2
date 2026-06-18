package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface QuestionService {
    void add(String questionText, String answerText);
    void add(Question question);
    boolean remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
