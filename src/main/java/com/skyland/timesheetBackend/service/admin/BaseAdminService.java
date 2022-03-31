package com.skyland.timesheetBackend.service.admin;

import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.model.User;

import java.util.List;

public interface BaseAdminService {

    public List<UserDto> getUsers();
    public User verifyUser(User user);
}
