package com.sachira.spring_backend.controller;

import com.sachira.spring_backend.dto.PostDTO;
import com.sachira.spring_backend.dto.UserDTO;
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
    private JWTAuthenticator jwtAuthenticator;

    //@Route GET /post
    //@Access Public
    //@Use Get all posts
    @GetMapping
    public List<PostDTO> get(){
        List<PostDTO> posts=postService.getPostAllPosts();
        return posts;
    }

    //@Route GET /post/{id}
    //@Access Public
    //@Use Get a post
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable Integer id){
        PostDTO post=postService.getPostById(id);
        if(post!=null){
            return new ResponseEntity<>(post,HttpStatus.OK);
        }

        return new ResponseEntity<>("Post not found!",HttpStatus.NOT_FOUND);
    }

    //@Route POST /post
    //@Access Private
    //@Use Add new post
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

    //@Route DELETE /post/{id}
    //@Access Private
    //@Use Delete a post
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        if(jwtAuthenticator.validateJwtToken(token)){
            PostDTO post=postService.getPostById(id);
            if(post==null)return new ResponseEntity<>("Post not found",HttpStatus.NOT_FOUND);
            User user=jwtAuthenticator.getUserByToken(token);
            if(user!=null){
                int postOwnerId=postService.getPostById(id).getUser();
                if(user.getId()==postOwnerId){
                    boolean result=postService.deletePost(id);
                    if(result){
                        return new ResponseEntity<>("Post Deleted!",HttpStatus.OK);
                    }
                }else{
                    return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
                }

            }else{
                return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Post not found",HttpStatus.NOT_FOUND);
    }

    //@Route PUT /post/{id}
    //@Access Private
    //@Use Update a post
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PostDTO postDTO,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        if(jwtAuthenticator.validateJwtToken(token)){
            PostDTO post=postService.getPostById(id);
            if(post==null)return new ResponseEntity<>("Post not found",HttpStatus.NOT_FOUND);
            User user=jwtAuthenticator.getUserByToken(token);
            if(user!=null){
                int postOwnerId = postService.getPostById(id).getUser();
                if (user.getId() == postOwnerId) {
                    Object result = postService.updatePost(postDTO, id);
                    if (result != null){
                        return new ResponseEntity<>(result,HttpStatus.OK);
                    }
                }else{
                    return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
                }
            }else{
                return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Post not found!",HttpStatus.NOT_FOUND);
    }

    //@Route PUT /post/upload/{id}
    //@Access Private
    //@Use Upload the post image
    @PostMapping("/upload/{id}")
    public ResponseEntity<Object> uploadPostImage(@PathVariable Integer id,@RequestParam(name = "file") MultipartFile file,@RequestHeader(HttpHeaders.AUTHORIZATION )String token) throws IOException {
        if(jwtAuthenticator.validateJwtToken(token)){
            PostDTO post=postService.getPostById(id);
            if(post==null)return new ResponseEntity<>("Post not found",HttpStatus.NOT_FOUND);
            User user=jwtAuthenticator.getUserByToken(token);
            if(user!=null){
                int postOwnerId = postService.getPostById(id).getUser();
                if (user.getId() == postOwnerId) {
                    PostDTO updatedPost = postService.uploadPostImage(file, id);
                    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
                }
            }else{
                return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
        }

    }

    //@Route GET /post/image/{id}
    //@Access Public
    //@Use Get post image
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getPostImage(@PathVariable Integer id) throws IOException {

        byte[] image=postService.getPostImage(id);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image,headers,HttpStatus.OK);
    }
    
}
