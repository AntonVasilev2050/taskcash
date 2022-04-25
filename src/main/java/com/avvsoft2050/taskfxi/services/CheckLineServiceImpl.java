package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.CheckLineRepository;
import com.avvsoft2050.taskfxi.model.Check;
import com.avvsoft2050.taskfxi.model.CheckLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckLineServiceImpl implements CheckLineService{

    @Autowired
    CheckLineRepository checkLineRepository;

    @Override
    public List<CheckLine> getAllCheckLines() {
        return checkLineRepository.findAll();
    }

    @Override
    public CheckLine saveCheckLine(CheckLine checkLine) {
        return checkLineRepository.save(checkLine);
    }

    @Override
    public CheckLine getCheckLine(int checkLineId) {
        CheckLine checkLine = new CheckLine();
        Optional<CheckLine> optional = checkLineRepository.findById(checkLineId);
        if(optional.isPresent()){
            checkLine = optional.get();
        }
        return checkLine;
    }

    @Override
    public void deleteCheckLineById(int checkLineId) {
        checkLineRepository.getById(checkLineId);
    }
}
