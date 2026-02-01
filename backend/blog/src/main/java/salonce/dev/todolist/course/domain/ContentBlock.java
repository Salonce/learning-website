package salonce.dev.todolist.course.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "content_blocks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "block_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ContentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private int position;

    void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    protected ContentBlock() {} // JPA

    protected ContentBlock(int position) {
        this.position = position;
    }

    public abstract String type();
}

