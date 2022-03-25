package com.skyland.timesheetBackend.service.admin;

import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service @RequiredArgsConstructor
public class AdminService implements BaseAdminService {

    private final UserRepo userRepo;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void verifyUser(String username) {
        User user = userRepo.findByUsername(username);
        user.setVerified(true);
    }
}
