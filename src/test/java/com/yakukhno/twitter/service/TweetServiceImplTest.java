package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration("classpath:/repoContext.xml"),
        @ContextConfiguration("classpath:/serviceContext.xml")
})
public class TweetServiceImplTest {
    @Autowired
    private TweetService tweetService = null;

    @Test
    public void getTweet() throws Exception {
        System.out.println("getTweet");
        int id = 1;
        Optional<Tweet> result = tweetService.getTweet(id);
        assertNotNull(result.orElse(null));
    }

    @Test
    public void getTweetNotFound() throws Exception {
        System.out.println("getTweet");
        int id = 0;
        Optional<Tweet> result = tweetService.getTweet(id);
        assertNull(result.orElse(null));
    }

}