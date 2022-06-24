package ru.otus.hw04studenttesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw04studenttesting.service.IOService;
import ru.otus.hw04studenttesting.service.LocalizationService;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class IOServiceImpl implements IOService {

    private final LocalizationService localizationService;

    @Override
    public String input() {
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printList(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String l : list) {
            builder.append("'").append(l).append("' ");
        }
        System.out.println(builder);
    }

    @Override
    public void printBundledMessage(String bundledMessage) {
        print(localizationService.getBundledMessage(bundledMessage));
    }
}