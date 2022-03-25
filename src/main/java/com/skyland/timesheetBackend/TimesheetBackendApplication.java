package com.skyland.timesheetBackend;

import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.Task;
import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.service.role.RoleService;
import com.skyland.timesheetBackend.service.task.TaskService;
import com.skyland.timesheetBackend.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class TimesheetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetBackendApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService, TaskService taskService) {
		return args -> {
			roleService.saveRole(new Role(null,"ROLE_USER"));
			roleService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser(new User("John","Travolta", "555 555 55 55","description","john", "1234", new ArrayList<>()));
			userService.saveUser(new User("Will","Smith", "555 555 55 55","description","will", "1234", new ArrayList<>()));
			userService.saveUser(new User("Jim","Carry", "555 555 55 55","description","jim", "1234", new ArrayList<>()));
			userService.saveUser(new User("Arnold","Sch", "555 555 55 55","description","arnold", "1234", new ArrayList<>()));


			roleService.addRoleToUser("jim", "ROLE_ADMIN");
			roleService.addRoleToUser("arnold", "ROLE_ADMIN");

			taskService.saveTask(new Task(null,"title","describtion",null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
			taskService.saveTask(new Task(null,"title2","describtion2",null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));


			taskService.addUserToTask("jim",Long.valueOf(7));
		};
	}
}
