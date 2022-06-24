package ru.otus.hw04studenttesting.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.service.ConsoleService;
import ru.otus.hw04studenttesting.service.IOService;
import ru.otus.hw04studenttesting.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class ShellComands {

    private final ConsoleService consoleService;
    private final QuizService quizService;
    private final IOService ioService;
    private final AppConfiguration configuration;

    @ShellMethod(value = "Выбрать язык.", key = {"language", "lang"})
    public String selectLanguage(String language) {
        if (configuration.getLanguages().contains(language)) {
            return consoleService.selectLanguage(language);
        } else {
            ioService.print(configuration.getSelectedLanguageNotExistMessage() + "\n");
            ioService.print(configuration.getSelectLanguageMessage());
            ioService.printList(configuration.getLanguages());
            return "";
        }
    }

    @ShellMethod(value = "Авторизация (имя, фамилия).", key = {"login", "l"})
    public void login(
            @ShellOption("--name") String name,
            @ShellOption("--surname") String surname
    ) {
        consoleService.login(name, surname);
    }

    @ShellMethod(value = "Начать тест.", key = {"start", "s"})
    public void quiz() {
        quizService.quiz();
    }

    @ShellMethod(value = "Показать результат.", key = {"result", "r"})
    public void showResult() {
        consoleService.showResult();
    }
}