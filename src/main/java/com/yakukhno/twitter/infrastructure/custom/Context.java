package com.yakukhno.twitter.infrastructure.custom;

public interface Context {
    <T> T getBean(String beanName) throws Exception;
}
