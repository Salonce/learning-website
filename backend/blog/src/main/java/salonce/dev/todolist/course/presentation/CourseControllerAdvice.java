package salonce.dev.todolist.course.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import salonce.dev.todolist.course.application.exceptions.CourseNotFound;
import salonce.dev.todolist.task.application.exceptions.ApiError;

public class CourseControllerAdvice {
    @ExceptionHandler(CourseNotFound.class)
    public ResponseEntity<ApiError> courseNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Course not found"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> accessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiError("Access forbidden"));
    }
}
