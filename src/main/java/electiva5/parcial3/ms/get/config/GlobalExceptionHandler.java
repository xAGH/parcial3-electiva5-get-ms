package electiva5.parcial3.ms.get.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import electiva5.parcial3.ms.get.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
                return new ResponseEntity<>(
                                new ApiResponse<>(false, ex.getMessage(), null),
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
