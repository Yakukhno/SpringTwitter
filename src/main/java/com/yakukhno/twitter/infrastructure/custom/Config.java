package com.yakukhno.twitter.infrastructure.custom;

public interface Config {
    Class<?> getImpl(String name);
}
