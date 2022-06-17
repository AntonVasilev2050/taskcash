package com.avvsoft2050.taskfxi.dao;

import com.avvsoft2050.taskfxi.entity.CheckLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckLineRepository extends JpaRepository<CheckLine, Integer>{
}
