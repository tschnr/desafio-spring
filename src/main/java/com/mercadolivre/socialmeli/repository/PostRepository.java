package com.mercadolivre.socialmeli.repository;

import com.mercadolivre.socialmeli.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p JOIN Users u ON (p.userId = u.userId) " +
            "JOIN Followers f ON (f.followedId.userId = p.userId) WHERE f.followerId.userId = ?1")
    List<Posts> resultList(Long userId);
}
