package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.CheckRepository;
import com.avvsoft2050.taskfxi.model.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckServiceImpl implements CheckService{

    @Autowired
    CheckRepository checkRepository;


    @Override
    public List<Check> getAllChecks() {
        return checkRepository.findAll();
    }

    @Override
    public Check saveCheck(Check check) {
        return checkRepository.save(check);
    }

    @Override
    public Check getCheck(int checkId) {
        Check check = new Check();
        Optional<Check> optional = checkRepository.findById(checkId);
        if(optional.isPresent()){
            check = optional.get();
        }
        return check;
    }

    @Override
    public void deleteCheckById(int checkId) {
        checkRepository.deleteById(checkId);
    }
}
