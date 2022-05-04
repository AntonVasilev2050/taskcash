package com.avvsoft2050.taskfxi;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskFxIApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TaskFxIApplication.class, args);

        // This is how normal Spring Boot app would be launched
        //SpringApplication.run(JavafxWeaverExampleApplication.class, args);

        Application.launch(JavaFxApplication.class, args);
    }

}
