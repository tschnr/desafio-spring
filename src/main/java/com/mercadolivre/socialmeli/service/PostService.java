package com.mercadolivre.socialmeli.service;


import com.mercadolivre.socialmeli.dto.FollowerPostsDTO;
import com.mercadolivre.socialmeli.dto.PostDTO;
import com.mercadolivre.socialmeli.dto.UserDTO;
import com.mercadolivre.socialmeli.exception.UserDoesNotExistsException;
import com.mercadolivre.socialmeli.exception.UserNotSeller;
import com.mercadolivre.socialmeli.model.Posts;
import com.mercadolivre.socialmeli.model.Users;
import com.mercadolivre.socialmeli.repository.PostRepository;
import com.mercadolivre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService{

    PostRepository postRepository;
    UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Posts addPost(Posts post){

        Users user = userRepository.findById(post.getUserId()).orElseThrow(UserDoesNotExistsException::new);
        if (!user.getSeller())  throw new UserNotSeller();

        postRepository.save(post);
        return post;
    }

    public FollowerPostsDTO findPostsFollowed(Long userId, String order){

        LocalDate twoWeekAgo = LocalDate.now().minusDays(14);

        List<Posts> posts = postRepository.resultList(userId).stream()
                .filter(post -> post.getDate().isAfter(twoWeekAgo))
                .filter(post -> post.getDate().isBefore(LocalDate.now().plusDays(1)))
                .collect(Collectors.toList());

        List<Posts> orderingDatePost = orderingDatePost(posts, order);

       FollowerPostsDTO followerPostsDTO = new FollowerPostsDTO();
       followerPostsDTO.setUserId(userId);
       followerPostsDTO.setPosts(orderingDatePost);

       return followerPostsDTO;
    }

    public List<Posts> orderingDatePost(List<Posts> listOrder, String order){
        List<Posts> ordering;

        if (order == null) {
            ordering = listOrder;

        } else if (order.equals("date_desc")) {
            ordering =  listOrder.stream()
                    .sorted(Comparator.comparing(Posts::getDate).reversed())
                    .collect(Collectors.toList());


        } else if (order.equals("date_asc")) {
            ordering =  listOrder.stream()
                    .sorted(Comparator.comparing(Posts::getDate))
                    .collect(Collectors.toList());
        } else {
            ordering = listOrder;
        }

        return ordering;

    }


}
