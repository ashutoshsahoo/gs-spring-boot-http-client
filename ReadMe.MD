# gs-spring-boot-http-client

Spring Declarative HTTP Client using @HttpExchange 

* Declaration

```java

@HttpExchange(url = "/users",
        accept = MediaType.APPLICATION_JSON_VALUE,
        contentType = MediaType.APPLICATION_JSON_VALUE)
public interface UserClient {

    @GetExchange
    List<UserDto> getAll();

    @GetExchange("/{id}")
    UserDto getById(@PathVariable("id") Long id);

    @PostExchange
    ResponseEntity<Void> save(@RequestBody UserDto userDto);

    @PutExchange("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserDto userDto);

    @DeleteExchange("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}

```

* Configuration

```java

@Configuration
public class UserClientConfig {

    @Bean
    RestClient restClient() {
        return RestClient.builder()
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

```