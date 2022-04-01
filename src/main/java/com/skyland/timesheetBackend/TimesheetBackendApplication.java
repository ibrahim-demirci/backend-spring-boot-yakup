package com.skyland.timesheetBackend;

import com.skyland.timesheetBackend.model.Role;
import com.skyland.timesheetBackend.model.Task;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.service.admin.AdminService;
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
	CommandLineRunner run(UserService userService, RoleService roleService, TaskService taskService, AdminService adminService) {
		return args -> {
			roleService.saveRole(new Role(null,"ROLE_USER"));
			roleService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser(new User("John","Travolta","IOS Dev", "555 555 55 55","description","john@gmail.com","john", "1234"));
			userService.saveUser(new User("Will","Smith", ".NET Dev","555 555 55 55","description","will@gmail.com","will", "1234"));
			userService.saveUser(new User("Jim","Carry", "Android Dev","555 555 55 55","description","jim@gmail.com","jim", "1234"));
			userService.saveUser(new User("Arnold","Sch", "UI Designer","555 555 55 55","description","arnold@gmail.com","arnold", "1234"));


			roleService.addRoleToUser("jim", "ROLE_ADMIN");
			roleService.addRoleToUser("arnold", "ROLE_ADMIN");

			taskService.saveTask(new Task(null,"title","describtion",null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
			taskService.saveTask(new Task(null,"title2","describtion2",null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));


			taskService.addUserToTask("jim",Long.valueOf(7));
			taskService.addUserToTask("jim",Long.valueOf(8));

			taskService.getTasksByUserId(Long.valueOf(5));

			adminService.verifyUser("arnold");
			adminService.verifyUser("jim");

		};
	}
}
