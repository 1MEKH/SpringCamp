package com.ua.studentstesting.service.task;

import com.ua.studentstesting.configs.AppSettings;
import com.ua.studentstesting.dao.TaskDaoCsv;
import com.ua.studentstesting.domain.Task;
import com.ua.studentstesting.service.io.IOServiceImpl;
import com.ua.studentstesting.service.massage.MassageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDaoCsv taskDao;
    private final AppSettings appSettings;
    private final MassageServiceImpl massageService;
    private final IOServiceImpl ioService;
    private int countOfCorrectAnswers;

    @Autowired
    public TaskServiceImpl(
            TaskDaoCsv taskDaoCsv, AppSettings appSettings,
            MassageServiceImpl massageService, IOServiceImpl ioService)
    {
        this.taskDao = taskDaoCsv;
        this.appSettings = appSettings;
        this.massageService = massageService;
        this.ioService = ioService;
    }

    @Override
    public void startTest() {
        countOfCorrectAnswers = 0;
        int answer;

        for(Task temp: getTasks()){
            ioService.out(temp.toString());
            answer = ioService.readInt();
            if(answer==temp.getCorrectAnswer()) {
                countOfCorrectAnswers++;
            }
        }
        showResult();
    }

    private void showResult(){

        if (countOfCorrectAnswers >= appSettings.getCountOfAnswersForSuccessfulTest()) {
            ioService.out(massageService.getMassage("message.test.successful"));
        } else {
            ioService.out(massageService.getMassage("message.test.fail"));
        }
        ioService.out(massageService.getMassage("message.test.correct") + " = " + countOfCorrectAnswers);
    }
    private List<Task> getTasks() {

        return this.taskDao.findAllTasks(appSettings.getCsvFileName());
    }

}
