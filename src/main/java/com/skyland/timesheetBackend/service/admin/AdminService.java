package com.skyland.timesheetBackend.service.admin;

import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional
public class AdminService implements BaseAdminService {

    private final UserRepo userRepo;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User verifyUser(User user) {
        User findedUser = userRepo.findByUsername(user.getUsername());
        findedUser.setVerified(true);
        return findedUser;
    }
}
