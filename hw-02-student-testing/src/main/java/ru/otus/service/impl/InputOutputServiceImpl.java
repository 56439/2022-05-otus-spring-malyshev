package ru.otus.service.impl;

import java.util.Scanner;

import ru.otus.service.InputOutputService;

public class InputOutputServiceImpl implements InputOutputService {

  @Override
  public void printMessage(String outputMessage) {
    System.out.println(outputMessage);
  }

  @Override
  public String inputAnswer() {
    return new Scanner(System.in).nextLine();
  }
}