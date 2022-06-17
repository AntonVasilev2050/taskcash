package com.avvsoft2050.taskfxi.dao;

import com.avvsoft2050.taskfxi.entity.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends JpaRepository<Check, Integer> {
}
