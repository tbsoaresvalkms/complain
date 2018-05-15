package com.tbsoares.complain;

import com.tbsoares.complain.repository.QueryRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableMongoRepositories(repositoryBaseClass = QueryRepositoryImpl.class)
public class ComplainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplainApplication.class, args);
    }
}
