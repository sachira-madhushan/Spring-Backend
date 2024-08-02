package com.sachira.spring_backend.service;

import com.sachira.spring_backend.dto.PostDTO;
import com.sachira.spring_backend.entity.Post;
import com.sachira.spring_backend.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostDTO createPost(PostDTO postDTO){
       Post savedPost= postRepo.save(new Post(postDTO.getTitle(),postDTO.getContent()));
       PostDTO savedPostDTO=new PostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
       return savedPostDTO;
    }

    public List<PostDTO> getPostAllPosts(){
        List<Post> posts=postRepo.findAll();
        List<PostDTO> postDTOS=new ArrayList<>();
        for(Post post:posts){
            postDTOS.add(new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getImage()));
        }

        return postDTOS;
    }

    public PostDTO getPostById(Integer id){
        Optional<Post> post=postRepo.findById(id);
        if(post.isPresent()){
            return new PostDTO(post.get().getId(), post.get().getTitle(), post.get().getContent(),post.get().getImage());
        }
        return null;
    }

    public boolean deletePost(Integer id){
        if(postRepo.existsById(id)){
            postRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public PostDTO updatePost(PostDTO postDTO,Integer id){
        if(postRepo.existsById(id)){
            Post post=postRepo.findById(id).get();
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            postRepo.save(post);

            return new PostDTO(post.getId(),post.getTitle(),post.getContent());
        }
        return null;
    }

    public PostDTO uploadPostImage(MultipartFile file,Integer id) throws IOException {
        Post post=postRepo.findById(id).get();
        String filename=file.getOriginalFilename();
        Path uploadPath= Paths.get("uploads/",filename);
        Files.createDirectories(uploadPath.getParent());
        Files.write(uploadPath,file.getBytes());
        post.setImage(uploadPath.toString());
        Post savedPost=postRepo.save(post);

        return new PostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(),savedPost.getImage());
    }

    public byte[] getPostImage(Integer id) throws IOException {
        Post post=postRepo.findById(id).get();
        String filename=post.getImage();
        Path uploadPath=Paths.get(filename);
        return Files.readAllBytes(uploadPath);
    }
}
