package com.ashu.practice.client;


import com.ashu.practice.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange(url = "/users",
        accept = MediaType.APPLICATION_JSON_VALUE,
        contentType = MediaType.APPLICATION_JSON_VALUE)
public interface UserClient {

    @GetExchange
    List<UserDto> getAll();

    @GetExchange
    List<UserDto> getAll(@RequestHeader("X-LOCAL-HEADER") String headerName);

    @GetExchange("/{id}")
    UserDto getById(@PathVariable("id") Long id);

    @PostExchange
    ResponseEntity<Void> save(@RequestBody UserDto userDto);

    @PutExchange("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserDto userDto);

    @DeleteExchange("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
