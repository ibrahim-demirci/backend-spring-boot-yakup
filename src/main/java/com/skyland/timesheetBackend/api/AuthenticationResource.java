package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.manager.ResponseManager;
import com.skyland.timesheetBackend.manager.responseModel.BaseResponse;
import com.skyland.timesheetBackend.model.User;
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

import static com.skyland.timesheetBackend.manager.ResponseManager.STATUS.created;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
@Slf4j

public class AuthenticationResource {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authenticate/signup").toUriString());
        try {
            userService.saveUser(user);
            BaseResponse base = ResponseManager.getInstance().getBaseResponse(created);
            return ResponseEntity.created(uri).body(base);

        } catch (Exception e) {

            BaseResponse base = ResponseManager.getInstance().getErrorResponseWithCustomMessage(e.getMessage());
            return ResponseEntity.created(uri).body(base);
        }
    }
}
