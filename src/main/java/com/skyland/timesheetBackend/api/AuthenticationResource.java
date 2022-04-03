package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.manager.responseModel.ErrorInfo;
import com.skyland.timesheetBackend.manager.responseModel.SignUpResponse;
import com.skyland.timesheetBackend.manager.ResponseManager;
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

import static com.skyland.timesheetBackend.constants.K.ErrorMessageInfo.*;
import static com.skyland.timesheetBackend.constants.K.ErrorMessageType.*;
import static com.skyland.timesheetBackend.constants.K.ResponseStatusUtilities.*;
import static com.skyland.timesheetBackend.manager.ResponseManager.STATUS.created;
import static com.skyland.timesheetBackend.manager.ResponseManager.STATUS.failed;

@RestController
@RequestMapping("/api/authenticate") @RequiredArgsConstructor
@Slf4j

public class AuthenticationResource {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/authenticate/signup").toUriString());
        try {
            userService.saveUser(user);
            ErrorInfo.BaseResponse base = ResponseManager.getInstance().get_base_response(created);
            return ResponseEntity.created(uri).body(base);

        } catch (Exception e) {
            if (e.getMessage() == USERNAME_ALREADY_TAKEN) {
                ErrorInfo errorInfo =
                        new ErrorInfo(
                                USERNAME_ALREADY_TAKEN,
                                USERNAME_ALREADY_TAKEN_INFO );
                ErrorInfo.BaseResponse base = ResponseManager.getInstance().get_base_response(failed);
                return ResponseEntity.created(uri).body(base);
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
