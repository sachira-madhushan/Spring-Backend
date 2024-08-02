package com.sachira.spring_backend.repo;

import com.sachira.spring_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
