package com.yakukhno.twitter;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.infrastructure.custom.ApplicationContext;
import com.yakukhno.twitter.infrastructure.custom.Config;
import com.yakukhno.twitter.infrastructure.custom.Context;
import com.yakukhno.twitter.infrastructure.custom.JavaConfig;
import com.yakukhno.twitter.service.TweetService;

public class TwitterRunner {
    public static void main(String[] args) throws Exception {
        Config config = new JavaConfig();
        Context context = new ApplicationContext(config);
        TweetService tweetService = context.getBean("tweetService");

        System.out.println(tweetService == context.getBean("tweetService"));
//        InitialContext context = new InitialContext(config);
//        TweetRepository tweetRepository = (TweetRepository) context.lookup("tweetRepo");
        tweetService.getAllTweets().forEach(System.out::println);
        tweetService.addTweet(new Tweet(3, new User(), "dass"));
    }
}
