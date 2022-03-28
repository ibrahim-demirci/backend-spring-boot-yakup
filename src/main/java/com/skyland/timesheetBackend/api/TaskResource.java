package com.skyland.timesheetBackend.api;

import com.skyland.timesheetBackend.dto.TaskDto;
import com.skyland.timesheetBackend.model.Task;
import com.skyland.timesheetBackend.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/task") @RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/task/save").toUriString());
        return ResponseEntity.created(uri).body(taskService.saveTask(task));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/fromuser/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTasksByUserId(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}
