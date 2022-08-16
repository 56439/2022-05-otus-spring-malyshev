package ru.otus.hw08library.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw08library.service.IOService;

@Service
public class IOServiceImpl implements IOService {

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}
