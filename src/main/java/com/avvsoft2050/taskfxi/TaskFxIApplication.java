package com.avvsoft2050.taskfxi;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskFxIApplication {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }

}
