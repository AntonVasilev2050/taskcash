package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.Check;

import java.util.List;

public interface CheckService {
    List<Check> getAllChecks();
    Check saveCheck(Check check);
    Check getCheck(int checkId);
    void deleteCheckById(int checkId);
}
