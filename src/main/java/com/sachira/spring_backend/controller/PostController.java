package com.sachira.spring_backend.controller;

import com.sachira.spring_backend.dto.PostDTO;
import com.sachira.spring_backend.entity.Post;
import com.sachira.spring_backend.repo.PostRepo;
import com.sachira.spring_backend.service.PostService;
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
    private PostService postService;
    public void get(){

    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO){
        PostDTO cretedPost=postService.createPost(postDTO);
        return new ResponseEntity<>(cretedPost, HttpStatus.CREATED);
    }

    public void delete(){

    }

    public void update(){

    }
}
