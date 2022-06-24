package ru.otus.hw04studenttesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw04studenttesting.dao.QuestionDao;
import ru.otus.hw04studenttesting.model.Question;
import ru.otus.hw04studenttesting.model.User;
import ru.otus.hw04studenttesting.service.IOService;
import ru.otus.hw04studenttesting.service.LocalizationService;
import ru.otus.hw04studenttesting.service.QuizService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionDao questionDao;
    private final IOService ioService;
    private final LocalizationService localizationService;
    private User user;
    private Integer result;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public void quiz() {
        String selectedLanguage = localizationService.getSelectedLanguage();

        List<Question> questions = questionDao.getAll(selectedLanguage);

        quizExecution(questions);
    }

    private void quizExecution(List<Question> questions) {
        int result = 0;
        for (Question question : questions) {
            ioService.print(question.getQuestion());

            String rightAnswer = question.getRightAnswer();
            String answer = ioService.input();

            if (checkAnswer(rightAnswer, answer)) result++;
        }
        setResult(result);
    }

    private boolean checkAnswer(String rightAnswer, String answer) {
        return rightAnswer.equalsIgnoreCase(answer);
    }
}