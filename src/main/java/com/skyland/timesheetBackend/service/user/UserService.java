package com.skyland.timesheetBackend.service.user;

import com.skyland.timesheetBackend.dto.UserDto;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.repo.UserRepo;
import com.skyland.timesheetBackend.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

import static com.skyland.timesheetBackend.utilities.ErrorMessageUtilities.ErrorMessageType.USERNAME_ALREADY_TAKEN;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements BaseUserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.error("User found in th database: {}", username);
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
        User findedUser = userRepo.findByUsername(user.getUsername());
        if (findedUser != null) {
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
        User user = userRepo.findByUsername(username);
        return convertEntityToDto(user) ;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);

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
