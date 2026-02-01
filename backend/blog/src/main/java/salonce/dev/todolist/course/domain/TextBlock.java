package salonce.dev.todolist.course.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "text_blocks")
public class TextBlock extends ContentBlock {

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    public TextBlock(String content) {
        this.content = content;
    }

    @Override
    public String type() {
        return "TEXT";
    }
}
