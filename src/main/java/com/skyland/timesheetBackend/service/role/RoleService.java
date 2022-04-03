package com.skyland.timesheetBackend.service.role;


import com.skyland.timesheetBackend.model.Role;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.repo.RoleRepo;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.skyland.timesheetBackend.constants.K.ErrorMessageType.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleService implements BaseRoleService {


    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} ", roleName,username);
        User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
}
