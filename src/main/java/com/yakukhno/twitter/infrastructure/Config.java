package com.yakukhno.twitter.infrastructure;

public interface Config {
    Class<?> getImpl(String name);
}
