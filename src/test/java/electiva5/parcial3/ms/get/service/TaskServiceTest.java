package electiva5.parcial3.ms.get.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import electiva5.parcial3.ms.get.model.Task;
import electiva5.parcial3.ms.get.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_shouldReturnTasks() {
        List<Task> tasks = List.of(Task.builder().id(1L).title("Test").build());
        when(taskRepository.findByDeletedAtIsNull()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        verify(taskRepository).findByDeletedAtIsNull();
    }

    @Test
    void getTaskById_shouldReturnTask() {
        Task task = Task.builder().id(1L).title("Sample").build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals("Sample", result.get().getTitle());
    }

    @Test
    void getTaskById_shouldReturnEmptyIfDeleted() {
        Task task = Task.builder().id(1L).title("Sample").deletedAt(LocalDateTime.now()).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertTrue(result.isEmpty());
    }
}
