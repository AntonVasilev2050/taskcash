package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.User;
import com.avvsoft2050.taskfxi.services.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/user_control.fxml")
public class Controller3 {

    private final UserServiceImpl userService;

    @Autowired
    public Controller3(UserServiceImpl userService) {
        this.userService = userService;
    }

    public TextField textFieldUserId;
    public TextField textFieldFirstName;
    public TextField textFieldLastName;
    public TextField textFieldPatronymic;
    public DatePicker datePickerBirthday;
    public TextField textFieldPassportId;
    public TextField textFieldSalary;
    public Label statusOfOperation;

    public void buttonGetUserByIdClicked() {
        long userId;
        try {
            userId = Integer.parseInt(textFieldUserId.getText());
            User user = userService.getUser(userId);
            textFieldFirstName.setText(user.getFirstName());
            textFieldLastName.setText(user.getLastName());
            textFieldPatronymic.setText(user.getPatronymic());
            datePickerBirthday.setValue(user.getBirthday());
            try {
                String passportId = String.valueOf(user.getPassportId());
                textFieldPassportId.setText(passportId);
            } catch (Exception e) {
                textFieldPassportId.setText("Data is not valid");
            }
            try {
                String salary = String.valueOf(user.getSalary());
                textFieldSalary.setText(salary);
            } catch (Exception e) {
                textFieldPassportId.setText("Data is not valid");
            }
            statusOfOperation.setText("The user information was successfully retrieved.");
        } catch (Exception e) {
            statusOfOperation.setText("User with Id " + textFieldUserId.getText() + " was not found");
            cleanFields();
        }
    }

    public void buttonSaveUserClicked() {
        long userId;
        User user = new User();
        try {
            userId = Long.parseLong(textFieldUserId.getText());
        } catch (Exception e) {
            userId = 0;
        }
        user.setUserId(userId);
        user.setFirstName(textFieldFirstName.getText());
        user.setLastName(textFieldLastName.getText());
        user.setPatronymic(textFieldPatronymic.getText());
        user.setBirthday(datePickerBirthday.getValue());
        user.setPassportId(Integer.parseInt(textFieldPassportId.getText()));
        user.setSalary(Integer.parseInt(textFieldSalary.getText()));
        User updatedUser = userService.saveUser(user);
        if (userId == updatedUser.getUserId()) {
            statusOfOperation.setText("The user with Id " + userId + " was updated successfully");
        } else {
            textFieldUserId.setText(String.valueOf(updatedUser.getUserId()));
            statusOfOperation.setText("New user with Id " + updatedUser.getUserId() + " was created");
        }
    }

    public void buttonDeleteUserClicked() {
        String statusMessage;
        long userId;
        try {
            userId = Long.parseLong(textFieldUserId.getText());
            /* ToDo make confirm dialog here

             */
            userService.deleteUserById(userId);
            statusMessage = "The user with Id " + userId + " was deleted successfully";
        } catch (NumberFormatException e) {
            statusMessage = "The user with Id " + textFieldUserId.getText() + " was not found";
        }
        statusOfOperation.setText(statusMessage);
    }

    private void cleanFields() {
        textFieldUserId.setText("");
        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldPatronymic.setText("");
        datePickerBirthday.setValue(null);
        textFieldPassportId.setText("");
        textFieldSalary.setText("");
    }
}
