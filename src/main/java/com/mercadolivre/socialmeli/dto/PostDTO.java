package com.mercadolivre.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PostDTO {
    private Long postId;
    private Long userId;
    private ProductDTO detail;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date date;
    private Integer category;
    private Double price;

    public PostDTO() {
    }

    public PostDTO(Long postId, Long userId, ProductDTO detail, Date date, Integer category, Double price) {
        this.postId = postId;
        this.userId = userId;
        this.detail = detail;
        this.date = date;
        this.category = category;
        this.price = price;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProductDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
