package ru.otus.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.otus.model.Question;
import ru.otus.model.User;
import ru.otus.service.InputOutputService;
import ru.otus.service.QuestionService;
import ru.otus.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

  private static final String RIGHT_ANSWERS_COUNT = ". Количество правильных ответов: ";
  private static final String ENTER_NAME_SURNAME = "Введите имя и фамилию: ";

  private final QuestionService questionService;
  private final InputOutputService inputOutputService;

  public QuizServiceImpl(QuestionService questionService, InputOutputService inputOutputService) {
    this.questionService = questionService;
    this.inputOutputService = inputOutputService;
  }

  @Override
  public void startQuiz() {
    User user = askName();

    List<Question> questions = questionService.getQuestions();

    int result = quizExecution(questions);

    showResults(user, result);
  }

  private User askName() {
    inputOutputService.printMessage(ENTER_NAME_SURNAME);
    String[] userData = inputOutputService.inputAnswer().split(" ");

    return new User(userData);
  }

  public Integer quizExecution(List<Question> questions) {
    int result = 0;
    for (Question question : questions) {
      inputOutputService.printMessage(question.getQuestion());

      String rightAnswer = question.getRightAnswer();
      String answer = inputOutputService.inputAnswer();

      if (checkAnswer(rightAnswer, answer)) result++;
    }
    return result;
  }

  private boolean checkAnswer(String rightAnswer, String answer) {
    return rightAnswer.equalsIgnoreCase(answer);
  }

  private void showResults(User user, int result) {
    StringBuilder resultMessage = new StringBuilder(user.getName());
    if (user.getSurname() != null) {
      resultMessage.append(" ").append(user.getSurname());
    }
    resultMessage.append(RIGHT_ANSWERS_COUNT).append(result);
    inputOutputService.printMessage(resultMessage.toString());
  }
}