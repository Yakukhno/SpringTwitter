package com.yakukhno.twitter.web.infrastructure;

import com.yakukhno.twitter.service.TweetService;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TweetController implements MyController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    public void handleRequest(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        tweetService.getAllTweets().forEach(out::println);
    }
}
