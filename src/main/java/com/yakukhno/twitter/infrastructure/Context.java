package com.yakukhno.twitter.infrastructure;

public interface Context {
    <T> T getBean(String beanName) throws Exception;
}
