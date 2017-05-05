package com.yakukhno.twitter.repository;

import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.infrastructure.Benchmark;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class InMemUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        users.add(new User(1, "Ivanov"));
        users.add(new User(2, "Petrov"));
        users.add(new User(3, "Sydorov"));
    }

    @Override
    @Benchmark(value = false)
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(users.size() + 1);
            users.add(user);
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    users.set(i, user);
                }
            }
        }
    }

    @Override
    public void update(User user) {
    }

    @Override
    public Optional<User> find(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    @Benchmark
    public Iterable<User> findAll() {
        return new ArrayList<>(users);
    }
}
