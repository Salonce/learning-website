package salonce.dev.todolist.course.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import salonce.dev.todolist.course.domain.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
