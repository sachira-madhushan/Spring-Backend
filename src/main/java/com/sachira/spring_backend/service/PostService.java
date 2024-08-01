package com.sachira.spring_backend.service;

import com.sachira.spring_backend.dto.PostDTO;
import com.sachira.spring_backend.entity.Post;
import com.sachira.spring_backend.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public PostDTO createPost(PostDTO postDTO){
       Post savedPost= postRepo.save(new Post(postDTO.getTitle(),postDTO.getContent()));
       PostDTO savedPostDTO=new PostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
       return savedPostDTO;
    }
}
