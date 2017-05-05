package com.yakukhno.twitter.repository;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.infrastructure.Benchmark;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {
    private List<Tweet> tweets = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        tweets.add(new Tweet(1, new User(), "First tweet"));
        tweets.add(new Tweet(2, new User(), "Second tweet"));
    }

    @Override
    @Benchmark(value = false)
    public void save(Tweet tweet) {
        tweets.add(tweet);
    }

    @Override
    public Optional<Tweet> find(int id) {
        return tweets.stream()
                .filter(tweet -> tweet.getTweetId() == id)
                .findFirst()
                .map(tweet -> tweet = new Tweet(tweet));
    }

    @Override
    @Benchmark
    public List<Tweet> findAll() {
        return new ArrayList<>(tweets)
                .stream()
                .map(tweet -> tweet = new Tweet(tweet))
                .collect(Collectors.toList());
    }
}
