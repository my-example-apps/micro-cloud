package com.example.microcloud;

import com.example.microcloud.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.Assert;

import javax.sql.DataSource;

//@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.microcloud.repo")
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }
}
