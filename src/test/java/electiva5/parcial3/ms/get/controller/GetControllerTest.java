package electiva5.parcial3.ms.get.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import electiva5.parcial3.ms.get.dto.ApiResponse;
import electiva5.parcial3.ms.get.model.Task;
import electiva5.parcial3.ms.get.service.TaskService;

@ActiveProfiles("test")
public class GetControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private GetController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task();
        when(taskService.getAllTasks()).thenReturn(List.of(task));

        ResponseEntity<ApiResponse<List<Task>>> response = taskController.getAllTasks();

        assertTrue(response.getBody().isSuccess());
        assertEquals(1, response.getBody().getData().size());
    }

    @Test
    void testGetTaskByIdFound() {
        Task task = new Task();
        task.setId(1L);
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));

        ResponseEntity<ApiResponse<Task>> response = taskController.getTaskById(1L);

        assertTrue(response.getBody().isSuccess());
        assertNotNull(response.getBody().getData());
        assertEquals(1L, response.getBody().getData().getId());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void testGetTaskByIdNotFound() {
        when(taskService.getTaskById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ApiResponse<Task>> response = taskController.getTaskById(1L);

        assertFalse(response.getBody().isSuccess());
        assertNull(response.getBody().getData());
        assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

}
