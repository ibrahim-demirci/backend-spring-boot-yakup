package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.constants.K;
import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.repo.UserRepo;
import com.skyland.timesheetBackend.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements BaseUserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String  email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new BadCredentialsException(K.ErrorMessageInfo.USER_NOT_FOUND_INFO + email));

        if (user.isVerified() == false) {
            throw new BadCredentialsException(K.ErrorMessageInfo.USER_NOT_VERIFIED_INFO);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) throws RuntimeException{
        log.info(user.toString());
        Optional<User> emailControlUser = userRepo.findByEmail(user.getEmail());
        Optional<User> phoneControlUser = userRepo.findByPhone(user.getPhone());
        if (emailControlUser.isPresent()) {
            throw new RuntimeException(K.ErrorMessageInfo.EMAIL_ALREADY_TAKEN_INFO);
        } else if (phoneControlUser.isPresent()) {
            throw new RuntimeException(K.ErrorMessageInfo.PHONE_ALREADY_USING);
        } else {
            user.setUserCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userSaved = userRepo.save(user);
            roleService.addRoleToUser(userSaved.getEmail(),"ROLE_USER");
            return userSaved;
        }
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException(K.ErrorMessageInfo.USER_NOT_FOUND_INFO));
        return convertEntityToDto(user) ;
    }

    @Override
    public UserDto getUserDtoByCode(String userCode) {
        User user = userRepo.findByUserCode(userCode).orElseThrow(() -> new RuntimeException(K.ErrorMessageInfo.USER_NOT_FOUND_INFO));
        return convertEntityToDto(user) ;
    }

    @Override
    public void deleteUserByCode(String userCode) {
        User user = userRepo.findByUserCode(userCode).orElseThrow(() -> new RuntimeException(K.ErrorMessageInfo.USER_NOT_FOUND_INFO + userCode));
        userRepo.deleteById(user.getId());
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException(K.ErrorMessageInfo.USER_NOT_FOUND_INFO));
        return user ;
    }

//    @Override
//    public User getUserById(Long id) {
//        return userRepo.findById(id).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND)) ;
//
//    }



    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUserCode(user.getUserCode());
        userDto.setJobTitle(user.getJobTitle());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setPhone(user.getPhone());
        userDto.setVerified(user.isVerified());
        userDto.setDescription(user.getDescription());
        return userDto;
    }

}
