package com.yakukhno.twitter.infrastructure;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.repository.TweetRepository;

public class TweetService {
    private TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    public Iterable<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
}
