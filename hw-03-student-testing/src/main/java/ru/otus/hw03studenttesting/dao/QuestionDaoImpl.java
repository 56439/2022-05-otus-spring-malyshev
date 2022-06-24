package ru.otus.hw03studenttesting.dao;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import ru.otus.hw03studenttesting.config.AppConfiguration;
import ru.otus.hw03studenttesting.model.Question;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {

    private final AppConfiguration configuration;

    @Override
    public List<Question> getAll(String language) {
        return fileToList(getFromResource(language));
    }

    @SneakyThrows
    private List<Question> fileToList(FileReader file) {
        List<Question> questions = new ArrayList<>();
        CSVReader csvReader = new CSVReader(file);
        String[] values;

        while ((values = csvReader.readNext()) != null) {
            questions.add(toModel(values));
        }

        return questions;
    }

    @SneakyThrows
    private FileReader getFromResource(String language) {
        String questions = configuration.getQuestionsResources().get(language);
        String resource = String.format("classpath:%s", questions);
        return new FileReader(ResourceUtils.getFile(resource));
    }

    private Question toModel(String[] question) {
        question = question[0].split(";");
        return new Question(Integer.parseInt(question[0]), question[1], question[2]);
    }
}