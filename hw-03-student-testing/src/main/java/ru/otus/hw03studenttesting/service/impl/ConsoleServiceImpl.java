package ru.otus.hw03studenttesting.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw03studenttesting.config.AppConfiguration;
import ru.otus.hw03studenttesting.model.User;
import ru.otus.hw03studenttesting.service.ConsoleService;
import ru.otus.hw03studenttesting.service.IOService;
import ru.otus.hw03studenttesting.service.LocalizationService;
import ru.otus.hw03studenttesting.service.QuizService;

import java.util.List;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private static final String RU_RU = "ру";
    private static final String RU_EN = "ru";

    private final String selectLanguageMessage;
    private final String selectedLanguageNotExistMessage;

    private final LocalizationService localizationService;
    private final IOService ioService;
    private final QuizService quizService;

    public ConsoleServiceImpl(AppConfiguration configuration, LocalizationService localizationService,
                              IOService ioService, QuizService quizService) {
        this.selectLanguageMessage = configuration.getSelectLanguageMessage();
        this.selectedLanguageNotExistMessage = configuration.getSelectedLanguageNotExistMessage();
        this.localizationService = localizationService;
        this.ioService = ioService;
        this.quizService = quizService;
    }

    @Override
    public void process() {
        selectLanguage();
        login();
        quizService.quiz();
        showResult();
    }

    private String selectLanguage() {
        List<String> languages = localizationService.getLanguages();
        ioService.print(selectLanguageMessage);
        ioService.printList(languages);
        String language = ioService.input();

        if (languages.contains(language)) {
            if (language.equals(RU_RU)) language = RU_EN;
            localizationService.setSelectedLanguage(language);
            return language;
        }
        ioService.print(selectedLanguageNotExistMessage + "\n");
        return selectLanguage();
    }

    private void login() {
        ioService.printBundledMessage("enter.name");
        String name = ioService.input();
        ioService.printBundledMessage("enter.surname");
        String surname = ioService.input();

        quizService.setUser(new User(name, surname));
    }

    private void showResult() {
        User user = quizService.getUser();
        String userNameSurname = user.getName() + " " + user.getSurname();

        String rightAnswersCount = localizationService.getBundledMessage("right.answers.count");
        Integer result = quizService.getResult();

        String resultMessage = userNameSurname + rightAnswersCount + " " + result;
        ioService.print(resultMessage);
    }
}