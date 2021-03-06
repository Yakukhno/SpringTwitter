package com.yakukhno.twitter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tweet {
    private int id;
    @JsonIgnore
    private User user;
    private String text;

    public Tweet() {
    }

    public Tweet(int id, User user, String text) {
        this.id = id;
        this.user = user;
        this.text = text;
    }

    public Tweet(Tweet tweet) {
        this.id = tweet.id;
        this.user = tweet.user;
        this.text = tweet.text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "text='" + text + '\'' +
                '}';
    }
}
