package com.micresservice.users.controller.users;

import com.micresservice.users.application.dto.UserDto;
import com.micresservice.users.application.save.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {

    @PostMapping("/user")
    ResponseEntity<Void> create(@RequestBody UserRequest userRequest);

    @GetMapping("/user/{id}")
    ResponseEntity<UserDto> findById(@PathVariable String id);

}
