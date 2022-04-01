package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.api.responseModel.SignUpResponse;
import com.skyland.timesheetBackend.api.responseModel.ErrorInfo;
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

import static com.skyland.timesheetBackend.utilities.ErrorMessageUtilities.ErrorMessageInfo.UNKNOWN_ERROR_INFO;
import static com.skyland.timesheetBackend.utilities.ErrorMessageUtilities.ErrorMessageType.USERNAME_ALREADY_TAKEN;
import static com.skyland.timesheetBackend.utilities.ErrorMessageUtilities.ErrorMessageInfo.USERNAME_ALREADY_TAKEN_INFO;
import static com.skyland.timesheetBackend.utilities.ErrorMessageUtilities.ErrorMessageType.UNKNOWN_ERROR;

import static com.skyland.timesheetBackend.utilities.ResponseStatusUtilities.STATUS_FAILED;
import static com.skyland.timesheetBackend.utilities.ResponseStatusUtilities.STATUS_CREATED;



@RestController
@RequestMapping("/api/authenticate") @RequiredArgsConstructor
@Slf4j

public class AuthenticationResource {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authenticate/signup").toUriString());
        try {
            User savedUser = userService.saveUser(user);
            SignUpResponse responseBody = new SignUpResponse(true, STATUS_CREATED,null);
            return ResponseEntity.created(uri).body(responseBody);

        } catch (Exception e) {
            if (e.getMessage() == USERNAME_ALREADY_TAKEN) {
                ErrorInfo errorInfo =
                        new ErrorInfo(
                                USERNAME_ALREADY_TAKEN,
                                USERNAME_ALREADY_TAKEN_INFO );
                SignUpResponse responseBody = new SignUpResponse(false, STATUS_FAILED, errorInfo );
                return ResponseEntity.created(uri).body(responseBody);
            }  else {
                ErrorInfo errorInfo =
                        new ErrorInfo(
                                UNKNOWN_ERROR,
                                UNKNOWN_ERROR_INFO );
                SignUpResponse responseBody = new SignUpResponse(false, STATUS_FAILED, errorInfo );
                return ResponseEntity.created(uri).body(responseBody);
            }
        }
    }
}
