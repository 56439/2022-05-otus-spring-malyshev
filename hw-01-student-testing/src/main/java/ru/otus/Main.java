package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.QuestionService;

public class Main {

  private final static String CONTEXT = "spring-context.xml";

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT);

    QuestionService questionService = context.getBean(QuestionService.class);

    System.out.println(questionService.getQuestions());
  }
}