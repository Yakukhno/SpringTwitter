package com.yakukhno.twitter.service;

import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.find(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
