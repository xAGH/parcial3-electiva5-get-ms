package electiva5.parcial3.ms.get.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electiva5.parcial3.ms.get.dto.ApiResponse;
import electiva5.parcial3.ms.get.model.Task;
import electiva5.parcial3.ms.get.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class GetController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(new ApiResponse<>(true, "Tasks retrieved successfully", tasks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable("id") Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(new ApiResponse<>(true, "Task found", task)))
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(false, "Task not found", null)));
    }

}
