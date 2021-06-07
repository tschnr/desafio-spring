package com.mercadolivre.socialmeli.dto;

public class FollowerCountDTO {
    private Long userId;
    private String userName;
    private Integer followers_count;

    public FollowerCountDTO(){

    }

    public FollowerCountDTO(Long userId, String userName, Integer followerCount) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followerCount;
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

    public Integer getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Integer followers_count) {
        this.followers_count = followers_count;
    }

}
