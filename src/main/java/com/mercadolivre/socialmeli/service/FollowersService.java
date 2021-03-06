package com.mercadolivre.socialmeli.service;

import com.mercadolivre.socialmeli.dto.*;
import com.mercadolivre.socialmeli.exception.*;
import com.mercadolivre.socialmeli.model.Followers;
import com.mercadolivre.socialmeli.model.Users;
import com.mercadolivre.socialmeli.repository.FollowersRepository;
import com.mercadolivre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowersService {

    FollowersRepository followersRepository;
    UserRepository userRepository;


    public FollowersService(FollowersRepository followersRepository, UserRepository userRepository){
        this.followersRepository = followersRepository;
        this.userRepository = userRepository;
    }

    public Followers addFollowers(Followers followers){
        return followersRepository.save(followers);
    }

    public FollowerCountDTO findFollowerCount(Long userId) throws UserDoesNotExistsException {

        FollowerCountDTO followerCountDTO = new FollowerCountDTO();
        Users users = userRepository.findByUserIdAndSeller(userId, true);

        if (users == null) throw new UserNotSeller();

        Integer usersFollow = followersRepository.findFollowersByFollowedId(users).size();

        followerCountDTO.setUserId(users.getUserId());
        followerCountDTO.setUserName(users.getName());
        followerCountDTO.setFollowers_count(usersFollow);
        return followerCountDTO;

    }

    public FollowersSellerDTO findFollowersSeller(Long userId, String order){
        Users usersFollowers = userRepository.findByUserIdAndSeller(userId, true);
        FollowersSellerDTO followersSellerDTO = new FollowersSellerDTO();

        if (usersFollowers == null) throw new UserNotSeller();

        List<UserDTO> usersFollow = followersRepository.findFollowersByFollowedId(usersFollowers).
                stream()
                .map(Followers::getFollowerId)
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());

        List<UserDTO> orderingName = orderingName(usersFollow, order);

        followersSellerDTO.setUserId(usersFollowers.getUserId());
        followersSellerDTO.setUserName(usersFollowers.getName());
        followersSellerDTO.setFollowers(orderingName);

        return followersSellerDTO;
    }

    public FollowedUserDTO findFollowedUser(Long userId, String order){
        Users usersFollowed = userRepository.findByUserIdAndSeller(userId, false);;
        FollowedUserDTO followedUserDTO = new FollowedUserDTO();

        if (usersFollowed == null) throw new UserIsSeller();

        List<UserDTO> sellersFollowed = followersRepository.findFollowersByFollowerId(usersFollowed)
                .stream()
                .map(Followers::getFollowedId)
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());

        List<UserDTO> orderingName = orderingName(sellersFollowed, order);

        followedUserDTO.setUserId(usersFollowed.getUserId());
        followedUserDTO.setUserName(usersFollowed.getName());
        followedUserDTO.setFollowed(orderingName);
        return  followedUserDTO;
    }

    //US0007
    public void unfollowSeller(Long userId, Long userIdToFollow) {
        Users userFollower = userRepository.findById(userId).orElseThrow(UserDoesNotExistsException::new);
        Users userFollowed = userRepository.findById(userIdToFollow).orElseThrow(UserDoesNotExistsException::new);

        Followers followers = findExistsFollow(userFollower, userFollowed);

        if(followers == null) throw new UserDoesNotFollowSeller();

        followersRepository.delete(followers);

    }

    public List<UserDTO> orderingName(List<UserDTO> listOrder, String order){
        List<UserDTO> ordering;

        if (order == null) {
            ordering = listOrder;

        } else if (order.equals("name_desc")) {
            ordering =  listOrder.stream()
                    .sorted(Comparator.comparing(UserDTO::getUserName).reversed())
                    .collect(Collectors.toList());


        } else if (order.equals("name_asc")) {
            ordering =  listOrder.stream()
                    .sorted(Comparator.comparing(UserDTO::getUserName))
                    .collect(Collectors.toList());
        } else {
            ordering = listOrder;
        }

        return ordering;

    }


    public Followers findExistsFollow(Users userId, Users userIdToFollow) {
        return followersRepository.findByFollowerIdAndFollowedId(userId, userIdToFollow);
    }

    public UserDTO convertToUserDTO (Users users){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(users.getUserId());
        userDTO.setUserName(users.getName());

        return userDTO;
    }

}
