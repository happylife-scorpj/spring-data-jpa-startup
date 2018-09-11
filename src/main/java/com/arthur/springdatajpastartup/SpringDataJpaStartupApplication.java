package com.arthur.springdatajpastartup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.arthur.springdatajpastartup"})
public class SpringDataJpaStartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaStartupApplication.class, args);
    }
}
