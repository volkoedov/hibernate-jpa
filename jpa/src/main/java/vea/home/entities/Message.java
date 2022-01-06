package vea.home.entities;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TEXT")
    private String text;

    public Message(String text) {
        this.text = text;
    }

    protected Message() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    public Long getId() {
        return id;
    }

}
