package com.mercadolivre.socialmeli.controller;

import com.mercadolivre.socialmeli.dto.FollowedUserDTO;
import com.mercadolivre.socialmeli.dto.FollowerCountDTO;
import com.mercadolivre.socialmeli.dto.FollowersSellerDTO;
import com.mercadolivre.socialmeli.exception.UserAlreadyFollowSeller;
import com.mercadolivre.socialmeli.exception.UserDoesNotExistsException;
import com.mercadolivre.socialmeli.exception.UserFollowedIsDoesNotSeller;
import com.mercadolivre.socialmeli.model.Followers;
import com.mercadolivre.socialmeli.model.Users;
import com.mercadolivre.socialmeli.service.FollowersService;
import com.mercadolivre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    FollowersService followersService;
    UserService userService;

    public UserController(FollowersService followersService, UserService userService){
        this.followersService = followersService;
        this.userService = userService;
    }

    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Followers> addFollowers(@PathVariable Long userId, @PathVariable Long userIdToFollow){

        Users userFollower = userService.findByUserId(userId);
        Users userFollowed = userService.findByUserId(userIdToFollow);
        Followers followers = followersService.findExistsFollow(userFollower, userFollowed);
        //Validates
        if (followers != null) throw new UserAlreadyFollowSeller();
        if (userId.equals(userIdToFollow)) throw new UserDoesNotExistsException();
        if (!userFollowed.getSeller()) throw new UserFollowedIsDoesNotSeller();

        Followers follow = new Followers();
        follow.setFollowerId(userFollower);
        follow.setFollowedId(userFollowed);
        followersService.addFollowers(follow);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}/followers/count")
    public ResponseEntity<FollowerCountDTO> findCountFollow(@PathVariable Long userId){

        FollowerCountDTO followerCountDTO = followersService.findFollowerCount(userId);
            return new ResponseEntity<>(followerCountDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/followers/list")
    public ResponseEntity<FollowersSellerDTO> findFollowersSeller(@PathVariable Long userId){

        FollowersSellerDTO followersSeller = followersService.findFollowersSeller(userId);
            return new ResponseEntity<>(followersSeller, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/followed/list")
    public ResponseEntity<FollowedUserDTO> findFollowedUser(@PathVariable Long userId){

        FollowedUserDTO followedUser = followersService.findFollowedUser(userId);
            return new ResponseEntity<>(followedUser, HttpStatus.OK);
    }

    @PostMapping(path = "/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity<Followers> unfollowSeller(@PathVariable Long userId, @PathVariable Long userIdToFollow){

        followersService.unfollowSeller(userId, userIdToFollow);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
