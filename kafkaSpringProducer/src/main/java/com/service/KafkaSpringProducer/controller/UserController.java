package com.service.KafkaSpringProducer.controller;

import com.service.KafkaSpringProducer.payload.UserRequest;
import com.service.KafkaSpringProducer.payload.UserResponse;
import com.service.KafkaSpringProducer.persistence.UserEntity;
import com.service.KafkaSpringProducer.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrder(@RequestBody UserRequest request) {
        UserEntity user = userService.createUser(request);
        UserResponse response  = UserResponse.builder().
                username(user.getUsername()).email(user.getEmail()).
                name(user.getName()).phone(user.getPhone()).build();
        log.info("response = " + response);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(response, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getUser() {
        List<UserEntity> user = userService.getUser();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(user, responseHeaders, HttpStatus.OK);
    }

}
