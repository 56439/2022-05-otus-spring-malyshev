package ru.otus.ivan.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.ivan.service.IOService;

@Service
public class IOServiceImpl implements IOService {

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}