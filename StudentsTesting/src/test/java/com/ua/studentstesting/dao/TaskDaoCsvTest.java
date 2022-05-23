package com.ua.studentstesting.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class TaskDaoCsvTest {

    @DisplayName("[+] Testing Dao CSV")
    @Test
    void findAllTasks() {
        TaskDaoCsv taskDaoCsv = new TaskDaoCsv();
        String questionActual = "Which programming language you choose?";

        String questionExpected = taskDaoCsv.findAllTasks("/testTasksData.csv").get(0).getQuestion();
        assertEquals(questionActual,questionExpected);

    }
}