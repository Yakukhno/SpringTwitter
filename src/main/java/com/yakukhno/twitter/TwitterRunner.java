package com.yakukhno.twitter;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.infrastructure.*;
import com.yakukhno.twitter.service.TweetService;
import com.yakukhno.twitter.service.TweetServiceImpl;

public class TwitterRunner {
    public static void main(String[] args) throws Exception {
        Config config = new JavaConfig();
        Context context = new ApplicationContext(config);
        TweetService tweetService = context.getBean("tweetService");

        System.out.println(tweetService == context.getBean("tweetService"));
//        InitialContext context = new InitialContext(config);
//        TweetRepository tweetRepository = (TweetRepository) context.lookup("tweetRepo");
        tweetService.getAllTweets().forEach(System.out::println);
        tweetService.addTweet(new Tweet(new User(), "dass"));
    }
}
