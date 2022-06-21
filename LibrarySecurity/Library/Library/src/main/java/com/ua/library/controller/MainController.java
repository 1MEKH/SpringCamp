package com.ua.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @PostMapping("/loginError")
    String loginErrorPage(){
        return "loginError";
    }


    @GetMapping("/welcome")
    String welcomePage(){
        return "welcome";
    }

    @GetMapping("/")
    String helloPage(){
        return "hello";
    }
}
