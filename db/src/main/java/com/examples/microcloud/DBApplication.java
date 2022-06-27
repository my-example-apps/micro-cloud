package com.examples.microcloud;

import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class DBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2Server() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9001");
    }

    @Bean
    public CommandLineRunner commandLineRunner(DataSource dataSource){
        return args -> {
            System.out.println(dataSource);
        };
    }
}
