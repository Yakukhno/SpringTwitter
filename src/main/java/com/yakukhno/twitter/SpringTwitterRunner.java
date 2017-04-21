package com.yakukhno.twitter;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.service.TweetService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTwitterRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext
                = new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext
                = new ClassPathXmlApplicationContext(new String[]{"serviceContext.xml"},
                                                     repoContext);

        TweetService tweetService
                = serviceContext.getBean("tweetService", TweetService.class);
        tweetService.getAllTweets().forEach(System.out::println);
        tweetService.addTweet(new Tweet(3, new User(), "dass"));

        System.out.println(tweetService.getTweet(3));

        repoContext.close();
        serviceContext.close();
    }
}
