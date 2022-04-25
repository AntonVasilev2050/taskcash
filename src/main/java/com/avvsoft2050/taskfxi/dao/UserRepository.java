package com.avvsoft2050.taskfxi.dao;

import com.avvsoft2050.taskfxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstNameEquals(String firstName);
}
