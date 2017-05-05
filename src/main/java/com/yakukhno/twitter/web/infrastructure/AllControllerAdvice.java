package com.yakukhno.twitter.web.infrastructure;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.service.TweetService;
import com.yakukhno.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

//@ControllerAdvice
public class AllControllerAdvice {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @InitBinder
    public void tweetBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tweet.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String s) throws IllegalArgumentException {
                int id = Integer.parseInt(s);
                Optional<Tweet> tweet = tweetService.getTweet(id);
                setValue(tweet.orElse(null));
            }
        });
    }


}
