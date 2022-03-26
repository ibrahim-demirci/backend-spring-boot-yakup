package com.skyland.timesheetBackend.service.task;

import com.skyland.timesheetBackend.model.Task;

import java.util.List;

public interface BaseTaskService {

    Task saveTask(Task task);
    Task getTask(Long taskId);
    List<Task> getTasks();
    public void addUserToTask(String  username, Long taskId);
    public void deleteTask(Long id);
}
