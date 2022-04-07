package com.skyland.timesheetBackend.service.role;


import com.skyland.timesheetBackend.constants.ErrorMessageInfo;
import com.skyland.timesheetBackend.model.Role;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.repo.RoleRepo;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {} ", roleName, email);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException(ErrorMessageInfo.USER_NOT_FOUND_INFO));
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
}
