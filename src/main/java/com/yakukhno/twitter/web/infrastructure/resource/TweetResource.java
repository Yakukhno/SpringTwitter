package com.yakukhno.twitter.web.infrastructure.resource;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.rest.RestTweetController;
import com.yakukhno.twitter.web.infrastructure.UserController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class TweetResource extends ResourceSupport {
    private final Tweet tweet;

    public TweetResource(Tweet tweet) {
        this.tweet = tweet;
        Link selfRel = linkTo(methodOn(RestTweetController.class).getTweet(tweet.getId()))
                .withSelfRel();
        Link userLink = linkTo(UserController.class)
                .slash(tweet.getUser().getId())
                .withRel("user");
        this.add(selfRel);
        this.add(userLink);
    }

    public Tweet getTweet() {
        return tweet;
    }
}
