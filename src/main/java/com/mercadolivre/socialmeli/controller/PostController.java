package com.mercadolivre.socialmeli.controller;


import com.mercadolivre.socialmeli.dto.FollowerPostsDTO;
import com.mercadolivre.socialmeli.model.Posts;
import com.mercadolivre.socialmeli.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PostController {

    PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //US0005
    @PostMapping("/newpost")
    public ResponseEntity<Posts> addPost(@Validated @RequestBody Posts post){

            postService.addPost(post);
            return new ResponseEntity<>(post, HttpStatus.CREATED);

    }

    //US0006 - US0009
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowerPostsDTO> listPostsFollowed(@PathVariable Long userId, String order){

        FollowerPostsDTO followerPostsDTOS = postService.findPostsFollowed(userId, order);
        return new ResponseEntity<>(followerPostsDTOS, HttpStatus.OK);

    }

}
