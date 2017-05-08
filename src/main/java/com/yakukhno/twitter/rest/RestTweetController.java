package com.yakukhno.twitter.rest;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.service.TweetService;
import com.yakukhno.twitter.web.infrastructure.resource.TweetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<TweetResource> allTweets() {
        return tweetService.getAllTweets().stream()
                        .map(TweetResource::new)
                        .collect(Collectors.toList());
    }

    @GetMapping(value = "/tweet/{id}", produces = "application/json")
    public ResponseEntity<TweetResource> getTweet(@PathVariable int id) {
        return tweetService.getTweet(id)
                .map(tweet -> ResponseEntity.ok(new TweetResource(tweet)))
                .orElse(ResponseEntity.notFound().build());
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
//                .slash(tweet.getId())
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
