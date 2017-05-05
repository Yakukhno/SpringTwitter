package com.yakukhno.twitter.repository;

import com.yakukhno.twitter.domain.Tweet;

import java.util.List;
import java.util.Optional;

public interface TweetRepository {
    void save(Tweet tweet);
    Optional<Tweet> find(int id);
    List<Tweet> findAll();
}
