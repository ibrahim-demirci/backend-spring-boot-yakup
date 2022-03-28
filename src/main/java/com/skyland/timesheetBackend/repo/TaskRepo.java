package com.skyland.timesheetBackend.repo;

import com.skyland.timesheetBackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
//    Task findByTaskId(Long taskId);

    @Query(value = "SELECT * FROM Task WHERE user_id = ?1", nativeQuery = true)
    List<Task> findByUserId(String user_id);
}
