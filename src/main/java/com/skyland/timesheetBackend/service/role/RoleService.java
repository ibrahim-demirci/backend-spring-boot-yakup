package com.skyland.timesheetBackend.service.role;


import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.repo.RoleRepo;
import com.skyland.timesheetBackend.repo.UserRepo;
import com.skyland.timesheetBackend.service.role.BaseRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements BaseRoleService {


    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
}
