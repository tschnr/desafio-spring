package com.mercadolivre.socialmeli.dto;

import java.util.List;

public class FollowedUserDTO {
    Long userId;
    String userName;
    List<UserDTO> followed;

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

    public List<UserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserDTO> followers) {
        this.followed = followers;
    }
}
