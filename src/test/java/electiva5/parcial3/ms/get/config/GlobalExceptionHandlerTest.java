package electiva5.parcial3.ms.get.config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import electiva5.parcial3.ms.get.dto.ApiResponse;

@ActiveProfiles("test")
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleGenericException_ReturnsInternalServerErrorWithExceptionMessage() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<ApiResponse<String>> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Something went wrong", response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void handleGenericException_WithNullMessage_ReturnsDefaultMessage() {
        Exception ex = new Exception("Test Error");

        ResponseEntity<ApiResponse<String>> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Test Error", response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }
}