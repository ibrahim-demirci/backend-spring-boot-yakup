package com.skyland.timesheetBackend.repo;

import com.skyland.timesheetBackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
