package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User getUser(long userId);
    void deleteUserById(long userId);
    List<User> findAllByFirstNameEquals(String firstName);
}
