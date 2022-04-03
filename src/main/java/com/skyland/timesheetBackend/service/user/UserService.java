package com.skyland.timesheetBackend.service.user;

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

import static com.skyland.timesheetBackend.constants.K.ErrorMessageType.*;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements BaseUserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));

        if (user.isVerified() == false) {
            throw new BadCredentialsException(USER_NOT_VERIFIED);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) throws Exception{
        log.info(user.toString());
        Optional<User> foundUser = userRepo.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            throw new Exception(USERNAME_ALREADY_TAKEN);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userSaved = userRepo.save(user);
            roleService.addRoleToUser(userSaved.getUsername(),"ROLE_USER");
            return userSaved;
        }

    }

    @Override
    public UserDto getUserDtoByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        return convertEntityToDto(user) ;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND)) ;

    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setPhone(user.getPhone());
        userDto.setVerified(user.isVerified());
        userDto.setDescription(user.getDescription());
        return userDto;
    }

}
