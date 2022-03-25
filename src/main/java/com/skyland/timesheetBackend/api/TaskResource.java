package com.skyland.timesheetBackend.api;

import com.skyland.timesheetBackend.domain.Role;
import com.skyland.timesheetBackend.domain.Task;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }
}
