package ru.otus.hw04studenttesting.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw04studenttesting.config.AppConfiguration;
import ru.otus.hw04studenttesting.service.ConsoleService;
import ru.otus.hw04studenttesting.service.IOService;
import ru.otus.hw04studenttesting.service.LocalizationService;
import ru.otus.hw04studenttesting.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class ShellComands {

    private static final String LANGUAGE_NOT_SELECTED_MESSAGE = "\nЯзык не выбран. | Language not selected.";

    private final ConsoleService consoleService;
    private final QuizService quizService;
    private final IOService ioService;
    private final AppConfiguration configuration;
    private final LocalizationService localizationService;

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
    @ShellMethodAvailability("loginAvailability")
    public void login(
            @ShellOption("--name") String name,
            @ShellOption("--surname") String surname
    ) {
        consoleService.login(name, surname);
    }

    @ShellMethod(value = "Начать тест.", key = {"start", "s"})
    @ShellMethodAvailability("quizAvailability")
    public void quiz() {
        quizService.quiz();
    }

    @ShellMethod(value = "Показать результат.", key = {"result", "r"})
    @ShellMethodAvailability("showResultAvailability")
    public void showResult() {
        consoleService.showResult();
    }

    private Availability loginAvailability() {
       return isLanguageSelected();
    }

    private Availability quizAvailability() {
       return isQuizAvailable();
    }

    private Availability showResultAvailability() {
        Availability quizAvailability = isQuizAvailable();
        Integer result = quizService.getResult();

        if (!quizAvailability.isAvailable()) return quizAvailability;
        if (result != null) return quizAvailability;

        String reason = quizAvailability.getReason();
        return Availability.unavailable(
                (reason == null ? "" : reason) +
                localizationService.getBundledMessage("shell.no-results")
        );
    }

    private Availability isUserLoggedIn() {
        if (!quizService.isUserLoggedIn()) {
            return Availability.unavailable(localizationService.getBundledMessage("shell.please-login"));
        }
        return Availability.available();
    }

    private Availability isLanguageSelected() {
        if (localizationService.getSelectedLanguage() == null) {
            return Availability.unavailable(LANGUAGE_NOT_SELECTED_MESSAGE);
        }
        return Availability.available();
    }

    private Availability isQuizAvailable() {
        if (!isLanguageSelected().isAvailable()) {
            return isLanguageSelected();
        }
        return isUserLoggedIn();
    }
}