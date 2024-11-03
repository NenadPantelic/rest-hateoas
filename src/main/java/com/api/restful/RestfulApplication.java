package com.api.restful;

import com.api.restful.seed.SeedHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApplication.class, args);
    }

    @Autowired
    private SeedHelper seedHelper;

    @Bean
    CommandLineRunner runner() {
        return args -> seedHelper.generate();
    }

}
