package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.api.model.ApiResponseBody;
import com.skyland.timesheetBackend.api.model.ErrorInfo;
import com.skyland.timesheetBackend.api.utilities.ErrorMessage;
import com.skyland.timesheetBackend.api.utilities.ResponseStatus;
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
            ApiResponseBody responseBody = new ApiResponseBody(true,ResponseStatus.STATUS_SUCCESS,null);
            return ResponseEntity.created(uri).body(responseBody);

        } catch (Exception e) {
           ErrorInfo errorInfo =
                    new ErrorInfo(
                            ErrorMessage.ErrorMessageType.USERNAME_ALREADY_TAKEN,
                            ErrorMessage.ErrorMessageInfo.USERNAME_ALREADY_TAKEN_INFO );
            ApiResponseBody responseBody = new ApiResponseBody(false, ResponseStatus.STATUS_FAILED, errorInfo );
            return ResponseEntity.created(uri).body(responseBody);
        }

    }
}
