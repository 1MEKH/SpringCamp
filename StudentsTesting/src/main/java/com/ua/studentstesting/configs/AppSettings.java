package com.ua.studentstesting.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppSettings {

    @Value("${app.countOfAnswersForSuccessfulTest}")
    private int countOfAnswersForSuccessfulTest;
    @Value("${app.language}")
    private String language;

    public int getCountOfAnswersForSuccessfulTest() {
        return countOfAnswersForSuccessfulTest;
    }

    public String getLanguage() {
        return language;
    }

    public Locale getLocale(){
        return new Locale(this.language);
    }

    public String getCsvFileName(){
        return "/data/tasks_"+this.language+".csv";
    }
}
