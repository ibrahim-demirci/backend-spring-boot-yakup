package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/authenticate") @RequiredArgsConstructor
@Slf4j
public class AuthenticationResource {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("message", "user-registered");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authenticate/signup").toUriString());
        return ResponseEntity.created(uri).headers(responseHeaders).body(userService.saveUser(user));
    }
}
