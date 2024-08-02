package com.sachira.spring_backend.entity;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String content;
    private String image;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    public Post() {

    }

//    public Post(int id, String title, String content, String image, User user) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.image = image;
//        this.user = user;
//    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
