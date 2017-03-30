package com.yakukhno.twitter.repository;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.infrastructure.Benchmark;
import com.yakukhno.twitter.infrastructure.PostConstructBean;

import java.util.ArrayList;
import java.util.List;

public class InMemTweetRepository implements TweetRepository {
    private List<Tweet> tweets = new ArrayList<>();

    @PostConstructBean
    public void initialize() {
        tweets.add(new Tweet(new User(), "First tweet"));
        tweets.add(new Tweet(new User(), "Second tweet"));
    }

    @Override
    @Benchmark(value = false)
    public void save(Tweet tweet) {
        tweets.add(tweet);
    }

    @Override
    @Benchmark
    public Iterable<Tweet> findAll() {
        return new ArrayList<>(tweets);
    }
}
