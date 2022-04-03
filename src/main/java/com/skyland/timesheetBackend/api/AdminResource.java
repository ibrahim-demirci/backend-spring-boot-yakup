package com.skyland.timesheetBackend.api;

import com.skyland.timesheetBackend.manager.responseModel.ErrorInfo;
import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.manager.ResponseManager;
import com.skyland.timesheetBackend.service.admin.BaseAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin") @RequiredArgsConstructor
public class AdminResource {

    private final BaseAdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PutMapping("/verify/{username}")
    public ResponseEntity<ErrorInfo.BaseResponse> verifyUser(@PathVariable("username") String username) {
        ErrorInfo.BaseResponse baseResponse = null;
        try {
            adminService.verifyUser(username);
            baseResponse = ResponseManager.getInstance().get_base_response(ResponseManager.STATUS.updated);
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e) {
            baseResponse = ResponseManager.getInstance().get_base_response(ResponseManager.STATUS.failed);
            return ResponseEntity.ok(baseResponse);
        }

    }
}
