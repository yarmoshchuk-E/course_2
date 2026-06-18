package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.*;

public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public void add(String questionText, String answerText) {
        add(new Question(questionText, answerText));
    }

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public boolean remove(Question question) {
        return questions.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questions.size());
// Преобразуем в список только для доступа по индексу, это ок для небольшого набора вопросов
        return new ArrayList<>(questions).get(index);
    }
}
