package com.yakukhno.twitter.repository;

import com.yakukhno.twitter.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    void update(User user);
    Optional<User> find(int id);
    Iterable<User> findAll();
}
