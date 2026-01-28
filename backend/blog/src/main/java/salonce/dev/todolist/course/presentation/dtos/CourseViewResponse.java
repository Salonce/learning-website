package salonce.dev.todolist.course.presentation.dtos;

import salonce.dev.todolist.course.domain.Lesson;

import java.util.List;

public record CourseViewResponse(Long id, String name, String slug, Integer orderId, List<Lesson> lessons){};
