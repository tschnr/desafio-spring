package com.mercadolivre.socialmeli.service;

import com.mercadolivre.socialmeli.dto.FollowerPostsDTO;
import com.mercadolivre.socialmeli.dto.PostCountPromoDTO;
import com.mercadolivre.socialmeli.dto.PostsBySeller;
import com.mercadolivre.socialmeli.exception.ProductIsNotPromo;
import com.mercadolivre.socialmeli.exception.ProductIsPromo;
import com.mercadolivre.socialmeli.exception.UserDoesNotExistsException;
import com.mercadolivre.socialmeli.exception.UserNotSeller;
import com.mercadolivre.socialmeli.model.Posts;
import com.mercadolivre.socialmeli.model.Users;
import com.mercadolivre.socialmeli.repository.PostRepository;
import com.mercadolivre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
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
        if (post.getHasPromo()) throw new ProductIsPromo();

        postRepository.save(post);
        return post;
    }

    public Posts addPromoPost(Posts post){

        Users user = userRepository.findById(post.getUserId()).orElseThrow(UserDoesNotExistsException::new);
        if (!user.getSeller())  throw new UserNotSeller();
        if (!post.getHasPromo()) throw new ProductIsNotPromo();

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

    public PostCountPromoDTO postCountPromo(Long userId){
        PostCountPromoDTO postCountPromoDTO = new PostCountPromoDTO();
        Users users = userRepository.findByUserIdAndSeller(userId, true);

        if (users == null) throw new UserNotSeller();

        Integer countPost = postRepository.findPostsByUserIdAndHasPromo(userId, true).size();

        postCountPromoDTO.setUserId(userId);
        postCountPromoDTO.setUserName(users.getName());
        postCountPromoDTO.setPromoproducts_count(countPost);


        return postCountPromoDTO;
    }

    public PostsBySeller postsBySeller(Long userId){
        PostsBySeller postsBySeller = new PostsBySeller();

        Users users = userRepository.findByUserIdAndSeller(userId, true);

        if (users == null) throw new UserNotSeller();

        List<Posts> posts = postRepository.findPostsByUserIdAndHasPromo(userId, true);

        postsBySeller.setUserId(userId);
        postsBySeller.setUserName(users.getName());
        postsBySeller.setPosts(posts);


        return postsBySeller;
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
