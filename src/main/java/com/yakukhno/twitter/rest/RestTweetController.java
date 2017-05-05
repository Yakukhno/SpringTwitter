package com.yakukhno.twitter.rest;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.service.TweetService;
import com.yakukhno.twitter.web.infrastructure.UserController;
import com.yakukhno.twitter.web.infrastructure.resource.TweetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RestTweetController {

    private final TweetService tweetService;

    @Autowired
    public RestTweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping(value = "/hello", produces = "application/json")
    public String[] helloREST() {
        return new String[]{"Hello from REST"};
    }

    @GetMapping(value = "/tweet", produces = "application/json")
    public Resources<TweetResource> allTweets() {
        return new Resources<>(
                tweetService.getAllTweets().stream()
                        .map(TweetResource::new)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/tweet/{id}", produces = "application/json")
    public ResponseEntity<TweetResource> getTweet(@PathVariable int id) {
        final HttpStatus[] httpStatus = {HttpStatus.NOT_FOUND};
        Optional<Tweet> tweetOptional = tweetService.getTweet(id);
        tweetOptional.ifPresent(tweet -> {
            httpStatus[0] = HttpStatus.OK;
        });
        return new ResponseEntity<>(tweetOptional.map(TweetResource::new).orElse(null),
                httpStatus[0]);
    }

    @PostMapping(value = "/tweet",
            consumes = "application/json",
            produces = "application/json")
    public Tweet addTweet(@RequestBody Tweet tweet) {
//        tweetService.addTweet(tweet);
        return tweet;
    }

//    private void addTweetLinks(Tweet tweet) {
//        Link selfRel = linkTo(RestTweetController.class)
//                .slash("tweet")
//                .slash(tweet.getTweetId())
//                .withSelfRel();
//        Link userLink = linkTo(UserController.class)
//                .slash(tweet.getUser().getId())
//                .withRel("user");
//        tweet.add(selfRel);
//        tweet.add(userLink);
//    }

//    @ModelAttribute("tweet")
//    public Tweet addTweetInModel(@PathVariable int id) {
//        return tweetService.getTweet(id).orElseThrow(NoSuchTweetException::new);
//    }
}
