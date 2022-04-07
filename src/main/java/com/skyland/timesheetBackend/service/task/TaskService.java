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
    public List<TaskDto> getTasks() {
        return taskRepo.findAll()
                .stream().map(
                        this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void addUserToTask(String email, Long taskId) throws RuntimeException {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with " + email));
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found with " + taskId));
        task.setUser(user);
    }

    @Override
    public void deleteTask(Long id) throws RuntimeException {
        taskRepo.findById(id).orElseThrow(() -> new RuntimeException(String.format("Task not found with %d", id)));
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
        taskDto.setEmail(task.getUser().getEmail());
        return taskDto;
    }
}
