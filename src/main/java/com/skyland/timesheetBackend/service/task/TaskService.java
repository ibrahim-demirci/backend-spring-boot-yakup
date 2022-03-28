package com.skyland.timesheetBackend.service.task;

import com.skyland.timesheetBackend.dto.TaskDto;
import com.skyland.timesheetBackend.model.Task;
import com.skyland.timesheetBackend.model.User;
import com.skyland.timesheetBackend.repo.TaskRepo;
import com.skyland.timesheetBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<TaskDto> getTasks() {
        return taskRepo.findAll()
                .stream().map(
                        this::convertEntityToDto).collect(Collectors.toList());
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

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }


    @Override
    public List<TaskDto> getTasksByUserId(Long userId) {
        return taskRepo.findTasksByUserId(userId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    private TaskDto convertEntityToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setTitle(task.getTitle());
        taskDto.setCreationDate(task.getCreationDate());
        taskDto.setPlannedFinishDate(task.getPlannedFinishDate());
        taskDto.setUsername(task.getUser().getUsername());
        return taskDto;
    }
}
