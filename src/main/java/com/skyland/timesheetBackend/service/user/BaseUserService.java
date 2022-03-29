package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.model.User;

public interface BaseUserService {
    User saveUser(User user) throws Exception;
    UserDto getUserDtoByUsername(String username);
    User getUserByUsername(String username);
    void deleteUser(Long id);
}
