package com.yakukhno.twitter.infrastructure;

import com.yakukhno.twitter.repository.InMemTweetRepository;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config{
    private final Map<String, Class<?>> classes = new HashMap<>();

    {
        classes.put("tweetRepository", InMemTweetRepository.class);
        classes.put("tweetService", TweetService.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }
}
