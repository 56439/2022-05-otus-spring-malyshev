package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.config.AppConfiguration;
import ru.otus.service.impl.QuizServiceImpl;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfiguration.class);

    QuizServiceImpl quizService = context.getBean(QuizServiceImpl.class);
    quizService.startQuiz();
    context.close();
  }
}