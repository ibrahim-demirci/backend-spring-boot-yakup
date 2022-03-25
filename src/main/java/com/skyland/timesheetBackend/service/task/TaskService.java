package com.skyland.timesheetBackend.service.task;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.skyland.timesheetBackend.domain.Task;
import com.skyland.timesheetBackend.domain.User;
import com.skyland.timesheetBackend.repo.TaskRepo;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskService implements BaseTaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;


    @Override
    public Task saveTask(Task task) {
       return taskRepo.save(task);
    }

    @Override
    public Task getTask(Long id) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    @Override
    public void addUserToTask(String username, Long taskId) throws RuntimeException{
        User user = userRepo.findByUsername(username);
        Optional<Task> optionalTask = taskRepo.findById(taskId);
        if(optionalTask.isPresent()) {
            optionalTask.get().setUser(user);
        } else {
            throw new RuntimeException("task not found");
        }


    }

}
