package salonce.dev.todolist.task.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import salonce.dev.todolist.shared.ApiError;
import salonce.dev.todolist.task.application.exceptions.TaskNotFound;

@RestControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ApiError> taskNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Task not found"));
    }
}
