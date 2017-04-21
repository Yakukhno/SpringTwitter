package com.yakukhno.twitter.web.infrastructure;

import com.yakukhno.twitter.domain.Tweet;
import com.yakukhno.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<b>Hello</b>");
        }
    }

    @GetMapping("/tweet/{id}")
    @ResponseBody
    public String findTweet(@PathVariable("id") Tweet tweet) {
        return tweet.toString();
    }

    @GetMapping("/modify/{id}")
    @ResponseBody
    public String modify(@PathVariable int id) {
        return tweetService.getTweet(id)
                .map(Tweet::toString)
                .orElse("Not found!");
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public String allTweets(Model model) {
        Iterable<Tweet> tweets = tweetService.getAllTweets();
        model.addAttribute("tweets", tweets);
        return "tweets";
    }

//    @InitBinder
//    public void tweetBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Tweet.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String s) throws IllegalArgumentException {
//                int id = Integer.parseInt(s);
//                Optional<Tweet> tweet = tweetService.getTweet(id);
//                setValue(tweet.orElse(null));
//            }
//        });
//    }
}
