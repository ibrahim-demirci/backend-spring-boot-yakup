package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.service.admin.BaseAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin") @RequiredArgsConstructor
public class AdminResource {

    private final BaseAdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PutMapping("/verify")
    public ResponseEntity<User> getEmployeeById(@RequestBody User user) {
        User updatedUser = adminService.verifyUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
