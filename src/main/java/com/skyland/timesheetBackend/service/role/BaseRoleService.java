package com.skyland.timesheetBackend.service.role;


import com.skyland.timesheetBackend.domain.Role;

public interface BaseRoleService {

    public Role saveRole(Role role);
    public void addRoleToUser(String username, String roleName);

}
