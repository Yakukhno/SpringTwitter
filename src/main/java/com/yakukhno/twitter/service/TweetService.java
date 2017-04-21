package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.Tweet;

import java.util.Optional;

public interface TweetService {
    void addTweet(Tweet tweet);
    Optional<Tweet> getTweet(int id);
    Iterable<Tweet> getAllTweets();
}
