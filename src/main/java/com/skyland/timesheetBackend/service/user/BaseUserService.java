package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.User;

import java.util.List;

public interface BaseUserService {
    User saveUser(User user) throws Exception;
    User getUser(String username);
    void deleteUser(Long id);
}
