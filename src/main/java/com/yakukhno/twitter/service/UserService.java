package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.User;

import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Optional<User> getUser(int id);
    Iterable<User> getAllUsers();
}
