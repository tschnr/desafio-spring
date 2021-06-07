package com.mercadolivre.socialmeli.repository;

import com.mercadolivre.socialmeli.model.Followers;
import com.mercadolivre.socialmeli.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {


    List<Followers> findFollowersByFollowerId(Users userId);

    List<Followers> findFollowersByFollowedId(Users userId);

    Followers findByFollowerIdAndFollowedId(Users followerId, Users followedId);

}
