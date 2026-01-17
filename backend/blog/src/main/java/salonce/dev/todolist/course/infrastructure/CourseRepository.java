package salonce.dev.todolist.course.infrastructure;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import salonce.dev.todolist.course.domain.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findBySlug(String slug);
    Optional<Course> findTopByOrderByOrderIndexDesc();

    @Query("SELECT c FROM Course c")
    @EntityGraph(attributePaths = {"lessons"})
    List<Course> findAllWithLessons();
}
