package cn.bugstack.middleware.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring-config.xml"})
public class ApiConsumerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiConsumerTestApplication.class, args);
    }

}