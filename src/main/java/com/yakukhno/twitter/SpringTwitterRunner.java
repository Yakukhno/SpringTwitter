package com.yakukhno.twitter;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.repository.TweetRepository;
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
        TweetRepository tweetRepository
                = repoContext.getBean("tweetRepository", TweetRepository.class);
        tweetRepository.findAll().forEach(System.out::println);
        tweetRepository.save(new Tweet(new User(), "dass"));

        TweetService tweetService
                = serviceContext.getBean("tweetService", TweetService.class);
        tweetService.getAllTweets().forEach(System.out::println);
        tweetService.addTweet(new Tweet(new User(), "dass"));

        repoContext.close();
        serviceContext.close();
    }
}
