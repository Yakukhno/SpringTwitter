package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.infrastructure.Benchmark;
import com.yakukhno.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Benchmark
    public Iterable<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
}
