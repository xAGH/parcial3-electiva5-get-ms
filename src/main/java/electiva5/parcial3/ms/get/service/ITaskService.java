package electiva5.parcial3.ms.get.service;

import java.util.List;
import java.util.Optional;

import electiva5.parcial3.ms.get.model.Task;

public interface ITaskService {
    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);
}
