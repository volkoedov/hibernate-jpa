package vea.home.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {
    @Id
    private String isbn;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "publisher_code")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST})
    private final Set<Chapter> chapters = new java.util.LinkedHashSet<>();

   public void addChapter(Chapter chapter){
       chapter.setBook(this);
       chapters.add(chapter);
   }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", publisher=" + publisher +
                ", chapters=" + chapters +
                '}';
    }

    public Book(String isbn, String name, Publisher publisher) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
    }

    protected Book() {
    }
}
