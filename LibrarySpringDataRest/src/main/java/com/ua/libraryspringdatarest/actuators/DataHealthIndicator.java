package com.ua.libraryspringdatarest.actuators;

import com.ua.libraryspringdatarest.repository.BookRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

//actuator
@Component
public class DataHealthIndicator implements HealthIndicator {

    private final int COUNT = 3;
    private final BookRepository bookRepository;

    public DataHealthIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        return bookRepository.count()==COUNT
                ? Health.up()
                .status(Status.UP)
                .withDetail("message","[+] Correct data in db")
                .build()
                : Health.down()
                .status(Status.DOWN)
                .withDetail("message","[-] Incorrect data in db")
                .build();
    }
}
