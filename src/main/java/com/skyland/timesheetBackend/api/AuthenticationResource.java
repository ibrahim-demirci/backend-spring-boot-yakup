package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.api.model.ApiResponseBody;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/authenticate") @RequiredArgsConstructor
@Slf4j
public class AuthenticationResource {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseBody> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authenticate/signup").toUriString());
        try {
            User savedUser = userService.saveUser(user);
            ApiResponseBody responseBody = new ApiResponseBody(true,"success",null);
            return ResponseEntity.created(uri).body(responseBody);

        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>() {{
                put("type","username-already-taken");
                put("info","This username is already taken." );
            }};
            ApiResponseBody responseBody = new ApiResponseBody(false,"failed", errorMap);
            return ResponseEntity.created(uri).body(responseBody);
        }

    }
}
