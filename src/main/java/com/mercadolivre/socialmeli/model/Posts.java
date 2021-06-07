package com.mercadolivre.socialmeli.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_d", referencedColumnName = "productId")
    private Products detail;

    @Column(name = "date")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @Column(name = "category")
    private Integer category;

    @Column(name = "price")
    private Double price;

    public Posts(){

    }

    public Posts(Long postId, Long userId, Products details, LocalDate date) {
        this.postId = postId;
        this.userId = userId;
        this.detail = details;
        this.date = date;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long post_id) {
        this.postId = post_id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Products getDetail() {
        return detail;
    }

    public void setDetail(Products detail) {
        this.detail = detail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
