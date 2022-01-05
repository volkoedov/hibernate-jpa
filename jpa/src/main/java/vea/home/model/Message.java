package vea.home.model;

public class Message {
    public Message(String text) {
        this.text = text;
    }

    protected Message() {
    }

    private Long id;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
