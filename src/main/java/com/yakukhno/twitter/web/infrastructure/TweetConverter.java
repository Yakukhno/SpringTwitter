package com.yakukhno.twitter.web.infrastructure;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class TweetConverter implements Converter<String, Tweet> {

    @Autowired
    private TweetService tweetService;

    @Override
    public Tweet convert(String s) {
        int id = Integer.parseInt(s);
        Optional<Tweet> tweet = tweetService.getTweet(id);
        return tweet.orElse(null);
    }
}
