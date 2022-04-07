package com.skyland.timesheetBackend.service.admin;

import com.skyland.timesheetBackend.dto.UserDto;

import java.util.List;

public interface BaseAdminService {

    public List<UserDto> getUsers();

    public UserDto verifyUser(String email) throws Exception;
}
