package com.sachira.spring_backend.controller;

import com.sachira.spring_backend.dto.PostDTO;
import com.sachira.spring_backend.entity.Post;
import com.sachira.spring_backend.entity.User;
import com.sachira.spring_backend.repo.PostRepo;
import com.sachira.spring_backend.service.PostService;
import com.sachira.spring_backend.utils.JWTAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    JWTAuthenticator jwtAuthenticator;

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
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){

        if(jwtAuthenticator.validateJwtToken(token)){

            User user=jwtAuthenticator.getUserByToken(token);
            if(user!=null){
                PostDTO createdPost=postService.createPost(postDTO,user);

                return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

            }else{
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
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

    @PostMapping("/upload/{id}")
    public ResponseEntity<PostDTO> uploadPostImage(@PathVariable Integer id,@RequestParam(name = "file") MultipartFile file) throws IOException {
        PostDTO post = postService.uploadPostImage(file, id);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getPostImage(@PathVariable Integer id) throws IOException {
        byte[] image=postService.getPostImage(id);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image,headers,HttpStatus.OK);
    }
    
}
