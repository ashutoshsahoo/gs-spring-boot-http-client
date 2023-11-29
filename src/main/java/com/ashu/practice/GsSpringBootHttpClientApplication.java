package com.ashu.practice;

import com.ashu.practice.client.UserClient;
import com.ashu.practice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class GsSpringBootHttpClientApplication implements CommandLineRunner {

    private final UserClient userClient;

    public static void main(String[] args) {
        SpringApplication.run(GsSpringBootHttpClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("-------Start-----");

        String headerValue = UUID.randomUUID().toString();
        log.info("getAll: {}", userClient.getAll(headerValue));

        log.info("getById: {}", userClient.getById(1L));
        var response = userClient.save(new UserDto(null, "ABC", "abc123", "abc@xyz.com"));
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("save successful");
        } else {
            log.error("Error occurred while saving");
        }

        var responseDelete = userClient.delete(1L);
        if (responseDelete.getStatusCode().is2xxSuccessful()) {
            log.info("delete successful");
        } else {
            log.error("Error occurred while deleting");
        }

        log.info("--------End-----");
    }
}
