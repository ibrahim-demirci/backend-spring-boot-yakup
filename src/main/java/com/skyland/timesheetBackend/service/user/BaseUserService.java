package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.model.User;

public interface BaseUserService {
    User saveUser(User user) throws Exception;
    User getUser(String username);
    void deleteUser(Long id);
}
