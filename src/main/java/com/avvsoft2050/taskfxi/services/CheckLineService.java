package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.Check;
import com.avvsoft2050.taskfxi.model.CheckLine;

import java.util.List;

public interface CheckLineService {
    List<CheckLine> getAllCheckLines();
    CheckLine saveCheckLine(CheckLine checkLine);
    CheckLine getCheckLine(int checkLineId);
    void deleteCheckLineById(int checkLineId);
}
