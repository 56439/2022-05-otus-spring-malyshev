package ru.otus.hw07library.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw07library.service.IOService;

@Service
public class IOServiceImpl implements IOService {

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}