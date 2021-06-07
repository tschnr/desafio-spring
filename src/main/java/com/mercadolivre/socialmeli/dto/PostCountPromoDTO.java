package com.mercadolivre.socialmeli.dto;

public class PostCountPromoDTO {
    private Long userId;
    private String userName;
    private Integer promoproducts_count;

    public PostCountPromoDTO() {
    }

    public PostCountPromoDTO(Long userId, String userName, Integer promoproducts_count) {
        this.userId = userId;
        this.userName = userName;
        this.promoproducts_count = promoproducts_count;
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

    public Integer getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(Integer promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
