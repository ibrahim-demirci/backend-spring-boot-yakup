package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authenticate/signup").toUriString());
        log.info("Sign up user is" +  user);
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
}
