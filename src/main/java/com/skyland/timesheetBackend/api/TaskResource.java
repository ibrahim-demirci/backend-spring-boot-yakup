package com.skyland.timesheetBackend.api;

import com.skyland.timesheetBackend.dto.TaskDto;
import com.skyland.timesheetBackend.manager.ResponseManager;
import com.skyland.timesheetBackend.manager.responseModel.BaseResponse;
import com.skyland.timesheetBackend.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

//    This feature for other task

//    @PostMapping("/task/save")
//    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/task/save").toUriString());
//        return ResponseEntity.created(uri).body(taskService.saveTask(task));
//    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/task/byuserid/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTasksByUserId(id));
    }

    @DeleteMapping("task/delete/{id}")
    public ResponseEntity<BaseResponse> deleteTask(@PathVariable("id") Long id) {
        BaseResponse response = null;
        try {
            taskService.deleteTask(id);
            response = ResponseManager.getInstance().get_base_response(ResponseManager.STATUS.deleted);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = ResponseManager.getInstance().get_error_response_with_custom_message(e.getMessage());
            return ResponseEntity.ok(response);
        }

    }
}
