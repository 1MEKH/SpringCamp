package com.ua.library.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService{
    private final Scanner sc;

    public IOServiceImpl() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public void out(String massage) {
        System.out.println(massage);
    }

    @Override
    public long readLong() {
        return sc.nextLong();
    }

    @Override
    public String readString() {
        sc.nextLine();
        return sc.nextLine();
    }
}
