package salonce.dev.todolist.course.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;
    private Integer orderIndex;
    private Boolean published;

    protected Course() {}

    public Course(String name, String slug, int orderIndex) {
        this.name = name;
        this.slug = slug;
        this.orderIndex = orderIndex;
        this.published = false;
    }

    @Setter
    @Embedded
    private Lessons lessons = new Lessons();

    public void addLesson(Lesson lesson) {
        lessons.addLesson(lesson, this);
    }

    public void removeLesson(Lesson lesson) {
        lessons.removeLesson(lesson);
    }
}