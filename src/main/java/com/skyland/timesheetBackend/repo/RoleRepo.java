package com.skyland.timesheetBackend.repo;

import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByUsername(String username);
}
