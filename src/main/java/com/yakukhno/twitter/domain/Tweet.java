package com.yakukhno.twitter.domain;

public class Tweet {
    private User user;
    private String text;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "text='" + text + '\'' +
                '}';
    }
}
