package com.ua.library.controller;


import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellController {
    private final JobLauncher jobLauncher;
    private final Job migrateMongoToMySql;

    public ShellController(JobLauncher jobLauncher, Job migrateMongoToMySql) {
        this.jobLauncher = jobLauncher;
        this.migrateMongoToMySql = migrateMongoToMySql;
    }

    @SneakyThrows
    @ShellMethod(value = "StartMigrationFromMongoToMysql", key = "sm-jl")
    public void startMigrationFromMongoToMySql() {
        jobLauncher.run(migrateMongoToMySql, new JobParameters());
    }
}