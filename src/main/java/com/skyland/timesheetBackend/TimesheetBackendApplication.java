package com.skyland.timesheetBackend;

import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.service.role.RoleService;
import com.skyland.timesheetBackend.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TimesheetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetBackendApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
			roleService.saveRole(new Role(null,"ROLE_USER"));
			roleService.saveRole(new Role(null,"ROLE_ADMIN"));


			userService.saveUser(new User(null,"John","Travolta", "555 555 55 55","description","john", "1234", new ArrayList<>(),false));
			userService.saveUser(new User(null,"Will","Smith", "555 555 55 55","description","will", "1234", new ArrayList<>(),false));
			userService.saveUser(new User(null,"Jim","Carry", "555 555 55 55","description","jim", "1234", new ArrayList<>(),false));
			userService.saveUser(new User(null,"Arnold","Sch", "555 555 55 55","description","arnold", "1234", new ArrayList<>(),false));

			roleService.addRoleToUser("john", "ROLE_USER");
			roleService.addRoleToUser("will", "ROLE_USER");
			roleService.addRoleToUser("jim", "ROLE_ADMIN");
			roleService.addRoleToUser("arnold", "ROLE_ADMIN");
			roleService.addRoleToUser("arnold", "ROLE_USER");
		};
	}
}
