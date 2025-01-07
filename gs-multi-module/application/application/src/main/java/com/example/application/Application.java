package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.multimodule")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
