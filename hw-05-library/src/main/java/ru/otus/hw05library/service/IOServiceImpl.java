package ru.otus.hw05library.service;

import org.springframework.stereotype.Service;

@Service
public class IOServiceImpl implements IOService {

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}