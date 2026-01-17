package salonce.dev.todolist.course.presentation;

import salonce.dev.todolist.course.domain.Lesson;
import salonce.dev.todolist.course.presentation.dtos.LessonViewResponse;

public class LessonMapper {
    public static LessonViewResponse toLessonViewResponse(Lesson lesson){
        return new LessonViewResponse(lesson.getId(), lesson.getTitle(), lesson.getSlug(), lesson.getOrderIndex());
    }
}
