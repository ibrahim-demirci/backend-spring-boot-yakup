package com.skyland.timesheetBackend.service.task;

import com.skyland.timesheetBackend.domain.Task;
import com.skyland.timesheetBackend.domain.User;

import java.util.List;

public interface BaseTaskService {

    Task saveTask(Task task);
    Task getTask(Long taskId);
    List<Task> getTasks();
    public void addUserToTask(String  username, Long taskId);
}
