package com.skyland.timesheetBackend.api;


import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") @RequiredArgsConstructor
public class AdminResource {

    private final AdminService adminService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }
}
