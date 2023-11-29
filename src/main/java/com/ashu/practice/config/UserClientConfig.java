package com.ashu.practice.config;

import com.ashu.practice.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class UserClientConfig {

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                // Enable request-response logging
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                //Globally Set Header - Once
                .defaultHeaders(header -> header.setBasicAuth("username", "P@ssword01"))
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    @Bean
    UserClient userClient(RestClient restClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                        .build();
        return httpServiceProxyFactory.createClient(UserClient.class);
    }
}
