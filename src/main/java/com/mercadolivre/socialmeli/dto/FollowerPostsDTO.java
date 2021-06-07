package com.mercadolivre.socialmeli.dto;

import com.mercadolivre.socialmeli.model.Posts;

import java.util.List;

public class FollowerPostsDTO {
    private Long userId;
    private List<Posts> posts;

    public FollowerPostsDTO() {}

    public FollowerPostsDTO(Long userId, List<Posts> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}
