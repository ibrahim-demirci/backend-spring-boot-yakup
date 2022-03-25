package com.skyland.timesheetBackend.repo;

import com.skyland.timesheetBackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
