package com.skyland.timesheetBackend.service.task;

import com.skyland.timesheetBackend.dto.TaskDto;
import com.skyland.timesheetBackend.model.Task;

import java.util.List;

public interface BaseTaskService {

    Task saveTask(Task task);
    Task getTask(Long taskId);
    List<TaskDto> getTasks();
    public void addUserToTask(String  username, Long taskId);
    public void deleteTask(Long id);
    List<Task> findTasksByUserId(String user_id);
}
