package com.ua.library.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.ua.library.repository")
@Configuration
public class MongoConfig {
}
