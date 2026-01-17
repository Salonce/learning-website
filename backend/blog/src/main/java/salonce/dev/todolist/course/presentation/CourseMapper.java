package salonce.dev.todolist.course.presentation;

import salonce.dev.todolist.course.domain.Course;
import salonce.dev.todolist.course.presentation.dtos.CourseViewResponse;

public class CourseMapper {
    public static CourseViewResponse toCourseViewResponse(Course course){
        return new CourseViewResponse(course.getId(), course.getName(), course.getSlug(), course.getOrderIndex());
    }
}
