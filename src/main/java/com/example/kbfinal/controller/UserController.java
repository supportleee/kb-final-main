package com.example.kbfinal.controller;

import com.example.kbfinal.dto.UserCountDto;
import com.example.kbfinal.dto.UserDto;
import com.example.kbfinal.entity.User;
import com.example.kbfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // user 정보를 입력, 삭제, 수정하는 API 생성
    @PostMapping("/")
    public UserDto createUser(@RequestBody User request) {
        // POST /api/users/ HTTP/1.1
        log.info("request: {}", request);

        return userService.registerUser(request);
    }

    @PutMapping("/{id}")
    public UserDto editUser(@PathVariable Long id, @RequestBody User request) {
        // PUT /api/users/{id} HTTP/1.1
        log.info("PUT /api/users/{} HTTP/1.1", id);

        return userService.editUser(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        // PUT /api/users/{id} HTTP/1.1
        log.info("PUT /api/users/{} HTTP/1.1", id);

        userService.deleteUser(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    // 전체 user List를 조회하는 api 생성
    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        // GET /api/users/ HTTP/1.1
        log.info("GET /api/users/ HTTP/1.1");

        return userService.getAllUsers();
    }

    // 전체 user 의 숫자를 조회하는 api 생성
    @GetMapping("/count")
    public UserCountDto getUsersCount() {
        // GET /api/users/count HTTP/1.1
        log.info("GET /api/users/count HTTP/1.1");

        return userService.getUsersCount();
    }
}
