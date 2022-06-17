package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.CheckLineRepository;
import com.avvsoft2050.taskfxi.entity.CheckLine;
import org.springframework.stereotype.Service;

@Service
public class CheckLineServiceImpl implements CheckLineService{

    final
    CheckLineRepository checkLineRepository;

    public CheckLineServiceImpl(CheckLineRepository checkLineRepository) {
        this.checkLineRepository = checkLineRepository;
    }


    @Override
    public CheckLine saveCheckLine(CheckLine checkLine) {
        return checkLineRepository.save(checkLine);
    }
}
