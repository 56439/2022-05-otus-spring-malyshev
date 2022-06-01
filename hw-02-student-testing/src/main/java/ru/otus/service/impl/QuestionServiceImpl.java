package ru.otus.service.impl;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import ru.otus.model.Question;
import ru.otus.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
  private final String questions;

  public QuestionServiceImpl(String questions) {
    this.questions = questions;
  }

  @Override
  public List<Question> getQuestions() {
    return fileToList(getFileFromResource());
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
  private FileReader getFileFromResource() {
    String resource = String.format("classpath:%s", questions);
    return new FileReader(ResourceUtils.getFile(resource));
  }

  private Question toModel(String[] question) {
    question = question[0].split(";");
    return new Question(Integer.parseInt(question[0]), question[1], question[2]);
  }
}