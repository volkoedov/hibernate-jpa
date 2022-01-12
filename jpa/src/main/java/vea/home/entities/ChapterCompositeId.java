package vea.home.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ChapterCompositeId implements Serializable {
    @Column(name = "chapter_num")
    private String num;

    @Column(name = "book_isbn")
    String isbn;


    public ChapterCompositeId(String num) {
        this.num = num;
    }

    protected ChapterCompositeId() {
    }
}
