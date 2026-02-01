package salonce.dev.todolist.course.presentation.mappers;

import salonce.dev.todolist.course.domain.Lesson;
import salonce.dev.todolist.course.presentation.dtos.LessonMetadataResponse;

public class LessonMapper {
    public static LessonMetadataResponse toLessonMetadataResponse(Lesson lesson){
        return new LessonMetadataResponse(lesson.getId(), lesson.getTitle(), lesson.getSlug(), lesson.getOrderId());
    }
}
