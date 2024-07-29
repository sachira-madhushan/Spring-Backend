package com.sachira.spring_backend.controller;

import com.sachira.spring_backend.entity.Post;
import com.sachira.spring_backend.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepo postRepo;
    public void get(){

    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post){
        Post cretedPost=postRepo.save(post);
        return new ResponseEntity<>(cretedPost, HttpStatus.CREATED);
    }

    public void delete(){

    }

    public void update(){

    }
}
