package com.ua.studentstesting.dao;

import com.ua.studentstesting.domain.Task;

import java.util.List;

public interface TaskDao {
    List<Task> findAllTasks(String fileName);
}
