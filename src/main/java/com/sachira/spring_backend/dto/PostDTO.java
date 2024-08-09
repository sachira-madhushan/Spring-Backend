package com.sachira.spring_backend.dto;


import com.sachira.spring_backend.entity.User;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String image;
    private int user_id;


    public PostDTO(int id, String title, String content, String image, int user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.setUser(user_id);
    }


    public PostDTO(int id, String title, String content, String image) {
        this.id = id;
        this.title = title;
        this.setContent(content);
        this.setImage(image);
    }


    public PostDTO() {

    }

    public PostDTO(String title, String content) {
        this.title = title;
        this.setContent(content);
    }

    public PostDTO(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.setContent(content);
    }

    public PostDTO(int id,String title, String content, int user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
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

    public int getUser() {
        return user_id;
    }

    public void setUser(int user_id) {
        this.user_id = user_id;
    }
}
