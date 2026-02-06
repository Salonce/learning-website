package salonce.dev.todolist.course.presentation.mappers;

import salonce.dev.todolist.course.domain.Course;
import salonce.dev.todolist.course.presentation.dtos.CourseResponse;

import java.util.Collections;

public class CourseMapper {
    public static CourseResponse toCourseResponse(Course course){
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getSlug(),
                course.getPosition(),
                course.getLessons() == null
                        ? Collections.emptyList()
                        : course.getLessons().stream()
                        .map(LessonMapper::toLessonMetadataResponse).toList()
        );
    }
}
