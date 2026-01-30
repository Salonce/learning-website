package salonce.dev.todolist.course.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import salonce.dev.todolist.shared.ApiError;

@RestControllerAdvice
public class CourseExceptionHandler {
    @ExceptionHandler(CourseNotFound.class)
    public ResponseEntity<ApiError> courseNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Course not found"));
    }
}
