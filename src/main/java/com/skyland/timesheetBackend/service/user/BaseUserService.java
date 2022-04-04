package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.model.User;

public interface BaseUserService {
    User saveUser(User user) throws Exception;
    UserDto getUserDtoByEmail(String username);
    UserDto getUserDtoByCode(String userCode);
    User getUserByEmail(String email);
//    User getUserById(Long id);
    void deleteUserByCode(String  userCode);
}
