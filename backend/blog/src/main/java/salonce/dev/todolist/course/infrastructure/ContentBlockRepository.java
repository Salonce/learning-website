package salonce.dev.todolist.course.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salonce.dev.todolist.course.domain.ContentBlock;

@Repository
public interface ContentBlockRepository extends JpaRepository<ContentBlock, Long> {
}
