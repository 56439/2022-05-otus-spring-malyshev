package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.InputOutputService;
import ru.otus.service.QuestionService;
import ru.otus.service.QuizService;
import ru.otus.service.impl.InputOutputServiceImpl;
import ru.otus.service.impl.QuestionServiceImpl;
import ru.otus.service.impl.QuizServiceImpl;

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfiguration {

    @Bean
    public QuizService quizService(QuestionService questionService, InputOutputService inputOutputService) {
        return new QuizServiceImpl(questionService, inputOutputService);
    }

    @Bean
    public QuestionService questionService(@Value("${quiz.questions}") String questions) {
        return new QuestionServiceImpl(questions);
    }

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl();
    }
}