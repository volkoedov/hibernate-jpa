package vea.home.entities;

import javax.persistence.*;

@Entity
public class Chapter {
    @EmbeddedId
    private ChapterCompositeId id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "book_isbn")
    @MapsId("isbn")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Chapter(ChapterCompositeId id, String title) {
        this.id = id;
        this.title = title;
    }

    public Chapter() {
    }

    @Override
    public String toString() {
        return "Chapter{" +
                ", title='" + title + '\'' +
                '}';
    }
}