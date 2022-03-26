package com.skyland.timesheetBackend.repo;

import com.skyland.timesheetBackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
//    Task findByTaskId(Long taskId);
}
