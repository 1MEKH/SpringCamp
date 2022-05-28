package com.ua.library.service.IO;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService{

    private final Scanner sc;

    public IOServiceImpl() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public void out(String text) {
        System.out.println(text);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }

    @Override
    public long readLong() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }
}
