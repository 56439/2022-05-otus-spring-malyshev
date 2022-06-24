package ru.otus.hw04studenttesting.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.model.User;
import ru.otus.hw04studenttesting.service.ConsoleService;
import ru.otus.hw04studenttesting.service.IOService;
import ru.otus.hw04studenttesting.service.LocalizationService;
import ru.otus.hw04studenttesting.service.QuizService;

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

    @Override
    public String selectLanguage(String language) {
        List<String> languages = localizationService.getLanguages();

        if (languages.contains(language)) {
            if (language.equals(RU_RU)) language = RU_EN;
            localizationService.setSelectedLanguage(language);
            return language;
        }
        ioService.print(selectedLanguageNotExistMessage + "\n");
        ioService.print(selectLanguageMessage);
        ioService.printList(languages);
        return selectLanguage(ioService.input());
    }

    @Override
    public User login(String name, String surname) {
        quizService.setUser(new User(name, surname));

        String authorizedAsMessage = String.format(
                localizationService.getBundledMessage("authorized-as"), name, surname
        );
        ioService.print(authorizedAsMessage);
        return new User(name, surname);
    }

    @Override
    public void showResult() {
        User user = quizService.getUser();
        String userNameSurname = user.getName() + " " + user.getSurname();

        String rightAnswersCount = localizationService.getBundledMessage("right.answers.count");
        Integer result = quizService.getResult();

        String resultMessage = userNameSurname + rightAnswersCount + " " + result;
        ioService.print(resultMessage);
    }

    private void selectLanguage() {
        ioService.print(selectLanguageMessage);
        ioService.printList(localizationService.getLanguages());
        String language = ioService.input();
        /*ioService.print(selectLanguage(language) + "\n");*/

        selectLanguage(language);
        String selectedLanguageMessage = String.format(
                localizationService.getBundledMessage("selected-language"), language
        ) + "\n";
        ioService.print(selectedLanguageMessage);
    }

    private void login() {
        ioService.printBundledMessage("enter.name");
        String name = ioService.input();
        ioService.printBundledMessage("enter.surname");
        String surname = ioService.input();

        login(name, surname);
    }
}