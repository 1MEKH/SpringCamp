package com.ua.studentstesting.service.console;

import com.ua.studentstesting.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class AppShellCommand {

    private final TaskService taskService;
    private String name;

    @Autowired
    public AppShellCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @ShellMethod(value = "LOGIN COMMAND", key = {"l","log","login"})
    public String login(
            @ShellOption(defaultValue = "Anon")String name
    ){
        this.name = name;
        return "WELCOME " + name;
    }

    @ShellMethod(value = "START TEST",key = {"s","start"})
    @ShellMethodAvailability(value = "isPersonRegistered")
    public String startTesting(){
        taskService.startTest();
        return this.name + " passed the test";
    }

    public Availability isPersonRegistered(){
        return name == null? Availability.unavailable("[-] Login -> write l, login")
                : Availability.available();
    }
}
