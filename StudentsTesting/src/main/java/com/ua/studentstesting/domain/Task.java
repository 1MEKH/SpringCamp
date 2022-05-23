package com.ua.studentstesting.domain;

import java.util.List;
import java.util.Objects;

public class Task {
    private String question;
    private List<String> answerOptions;
    private int correctAnswer;

    public Task(String question, List<String> answerOptions, int correctAnswer) {
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return question + "\n"
                + answerOptions.get(0) + "\n"
                + answerOptions.get(1) + "\n"
                + answerOptions.get(2) + "\n"
                + answerOptions.get(3) + "\n"
                + "answer :: ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return correctAnswer == task.correctAnswer && Objects.equals(question, task.question) && Objects.equals(answerOptions, task.answerOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answerOptions, correctAnswer);
    }
}
