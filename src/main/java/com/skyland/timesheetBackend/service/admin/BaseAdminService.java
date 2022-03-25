package com.skyland.timesheetBackend.service.admin;

import com.skyland.timesheetBackend.domain.User;

import java.util.List;

public interface BaseAdminService {

    public List<User> getUsers();
    public void verifyUser(String username);
}
