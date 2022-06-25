package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.CheckRepository;
import com.avvsoft2050.taskfxi.entity.Check;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService{

    private final CheckRepository checkRepository;

    public CheckServiceImpl(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @Override
    public Check saveCheck(Check check) {
        return checkRepository.save(check);
    }
}
