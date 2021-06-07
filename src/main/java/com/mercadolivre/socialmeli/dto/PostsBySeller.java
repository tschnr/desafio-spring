package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.model.Posts;

import java.util.List;

public class PostsBySeller {
    private Long userId;
    private String userName;
    private List<Posts> posts;

    public PostsBySeller() {
    }

    public PostsBySeller(Long userId, String userName, List<Posts> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}
