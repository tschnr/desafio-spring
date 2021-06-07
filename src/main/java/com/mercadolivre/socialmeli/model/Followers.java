package com.mercadolivre.socialmeli.model;

import javax.persistence.*;

@Entity
@Table(name = "followers")

public class Followers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long followers_id;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "userId")
    Users followerId;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "userId")
    Users followedId;

    public Followers(){

    }

    public Followers(Long followers_id, Users followerId, Users followedId) {
        this.followers_id = followers_id;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Long getFollowers_id() {
        return followers_id;
    }

    public void setFollowers_id(Long followers_id) {
        this.followers_id = followers_id;
    }

    public Users getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Users follower_id) {
        this.followerId = follower_id;
    }

    public Users getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Users followed_id) {
        this.followedId = followed_id;
    }

}
