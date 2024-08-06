package com.sachira.spring_backend.repo;

import com.sachira.spring_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Integer> {

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(int id);
}
