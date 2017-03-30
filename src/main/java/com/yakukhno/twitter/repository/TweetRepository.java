package com.yakukhno.twitter.repository;

import com.yakukhno.twitter.domain.Tweet;

public interface TweetRepository {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
}
