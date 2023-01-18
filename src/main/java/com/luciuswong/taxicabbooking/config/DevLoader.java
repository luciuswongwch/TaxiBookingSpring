package com.luciuswong.taxicabbooking.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevLoader implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // nothing to run on development start up for now
    }
}
