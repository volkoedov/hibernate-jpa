package vea.home.entities;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String enrollmentId;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "guide_id")
    private Guide guide;

    protected Student() {
    }

    public Student(String enrollmentId, String name, Guide guide) {
        this.enrollmentId = enrollmentId;
        this.name = name;
        this.guide = guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public Guide getGuide() {
        return guide;
    }
}
