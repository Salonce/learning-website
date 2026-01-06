package salonce.dev.todolist.course.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import salonce.dev.todolist.account.domain.Identities;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;
    private Integer orderIndex;

    protected Course() {}

    public Course(String name, String slug, int orderIndex) {
        this.name = name;
        this.slug = slug;
        this.orderIndex = orderIndex;
    }

    @Setter
    @Embedded
    private Lessons lessons = new Lessons();
}