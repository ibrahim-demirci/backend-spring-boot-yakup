package com.skyland.timesheetBackend.service.task;

import com.skyland.timesheetBackend.dto.TaskDto;
import com.skyland.timesheetBackend.model.Task;

import java.util.List;

interface BaseTaskService {

    Task saveTask(Task task);

    List<TaskDto> getTasks();

    public void addUserToTask(String username, Long taskId);

    public void deleteTask(Long id);

    List<TaskDto> getTasksByUserId(Long user_id);
}
