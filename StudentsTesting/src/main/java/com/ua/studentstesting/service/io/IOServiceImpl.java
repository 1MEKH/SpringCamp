package com.ua.studentstesting.service.io;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner sc;

    public IOServiceImpl() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public void out(String massage) {
        System.out.println(massage);
    }

    @Override
    public int readInt() {
        return sc.nextInt();
    }
}
