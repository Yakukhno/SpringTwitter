package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.infrastructure.Benchmark;
import com.yakukhno.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("tweetService")
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public Optional<Tweet> getTweet(int id) {
        return tweetRepository.find(id);
    }

    @Override
    @Benchmark
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
}
