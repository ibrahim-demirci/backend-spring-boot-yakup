package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.api.responseModel.ErrorInfo;
import com.skyland.timesheetBackend.api.responseModel.VerifyResponse;
import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.service.admin.BaseAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skyland.timesheetBackend.constants.K.ErrorMessageInfo.USER_NOT_FOUND_INFO;
import static com.skyland.timesheetBackend.constants.K.ErrorMessageType.USER_NOT_FOUND;
import static com.skyland.timesheetBackend.constants.K.ResponseStatusUtilities.STATUS_FAILED;
import static com.skyland.timesheetBackend.constants.K.ResponseStatusUtilities.STATUS_UPDATED;

@RestController
@RequestMapping("/api/admin") @RequiredArgsConstructor
public class AdminResource {

    private final BaseAdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PutMapping("/verify/{username}")
    public ResponseEntity<VerifyResponse> verifyUser(@PathVariable("username") String username) {
        try {
            adminService.verifyUser(username);
            VerifyResponse responseBody = new VerifyResponse(true, STATUS_UPDATED, null );
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            ErrorInfo errorInfo =
                    new ErrorInfo(
                            USER_NOT_FOUND,
                            USER_NOT_FOUND_INFO );
            VerifyResponse responseBody = new VerifyResponse(false, STATUS_FAILED, errorInfo );
            return ResponseEntity.ok(responseBody);
        }

    }
}
