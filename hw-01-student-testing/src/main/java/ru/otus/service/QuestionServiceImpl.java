package ru.otus.service;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final String questions;

  @SneakyThrows
  public String getQuestions() {

    URI uri = Objects.requireNonNull(
        getClass().getResource(String.format("/%s", questions))
    ).toURI();

    if ("jar".equals(uri.getScheme())) {
      String[] array = uri.toString().split("!");
      FileSystems.newFileSystem(URI.create(array[0]), Collections.emptyMap());
    }

    return Files.readString(Paths.get(uri));
  }
}
