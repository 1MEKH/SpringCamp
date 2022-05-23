package com.ua.studentstesting.dao;

import com.ua.studentstesting.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class TaskDaoCsv implements TaskDao {

    @Override
    public List<Task> findAllTasks(String fileName) {
        InputStream inputStream = TaskDaoCsv.class.getResourceAsStream(fileName);
        List<Task> tasks = new ArrayList<>();
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);

        String[] tempLine;
        while (scanner.hasNextLine()) {
            tempLine = scanner.nextLine().split(",");
            tasks.add(new Task(tempLine[0],
                    Arrays.asList(tempLine[1].split(";")),
                    Integer.parseInt(tempLine[2])));
        }
        return tasks;
    }

}
