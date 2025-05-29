package electiva5.parcial3.ms.get.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import electiva5.parcial3.ms.get.model.Task;
import electiva5.parcial3.ms.get.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findByDeletedAtIsNull();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id).filter(task -> !task.isDeleted());
    }
}
