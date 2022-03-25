package com.skyland.timesheetBackend.service;

import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.User;

import java.util.List;

public interface BaseUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);

}
