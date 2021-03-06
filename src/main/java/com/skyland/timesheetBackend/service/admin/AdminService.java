package com.skyland.timesheetBackend.service.admin;

import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class AdminService implements BaseAdminService {

    private final UserRepo userRepo;

    @Override
    public List<UserDto> getUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto verifyUser(String email) throws Exception {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not fount with " + email));
        user.setVerified(true);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setJobTitle(user.getJobTitle());
        userDto.setDescription(user.getDescription());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setVerified(user.isVerified());
        return userDto;
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setJobTitle(user.getJobTitle());
        userDto.setDescription(user.getDescription());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setUserCode(user.getUserCode());
        userDto.setVerified(user.isVerified());
        return userDto;
    }
}
