package com.yakukhno.twitter.infrastructure;

public class InitialContext {
    private Config config;

    public InitialContext(Config config) {
        this.config = config;
    }

    public Object lookup(String name) throws Exception {
        return config.getImpl(name).newInstance();
    }
}
