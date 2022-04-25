package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.Check;
import com.avvsoft2050.taskfxi.model.User;

import java.util.List;

public interface CheckService {
    List<Check> getAllChecks();
    Check saveCheck(Check check);
    Check getCheck(int checkId);
    void deleteCheckById(int checkId);
}
