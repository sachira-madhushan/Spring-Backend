package com.sachira.spring_backend.repo;

import com.sachira.spring_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
