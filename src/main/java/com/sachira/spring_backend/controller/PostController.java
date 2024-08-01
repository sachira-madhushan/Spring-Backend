package com.sachira.spring_backend.controller;

import com.sachira.spring_backend.dto.PostDTO;
import com.sachira.spring_backend.entity.Post;
import com.sachira.spring_backend.repo.PostRepo;
import com.sachira.spring_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDTO> get(){
        List<PostDTO> posts=postService.getPostAllPosts();
        return posts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable Integer id){
        PostDTO post=postService.getPostById(id);
        if(post!=null){
            return new ResponseEntity<>(post,HttpStatus.OK);
        }

        return new ResponseEntity<>("Post not found!",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO){
        PostDTO cretedPost=postService.createPost(postDTO);
        return new ResponseEntity<>(cretedPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean result=postService.deletePost(id);
        if(result){
            return new ResponseEntity<>("Post Deleted!",HttpStatus.OK);
        }

        return new ResponseEntity<>("Post not found!",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PostDTO postDTO){
        Object result=postService.updatePost(postDTO,id);
        if(result!=null){
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>("Post not found!",HttpStatus.NOT_FOUND);
    }
}
