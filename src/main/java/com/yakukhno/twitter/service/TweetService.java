package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.Tweet;

public interface TweetService {
    void addTweet(Tweet tweet);
    Iterable<Tweet> getAllTweets();
}
