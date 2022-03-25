package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements BaseUserService {
    private final UserRepo userRepo;


    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

}
