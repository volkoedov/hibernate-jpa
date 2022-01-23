package vea.home.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Cacheable
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String enrollmentId;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getName() {
        return name;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Long getId() {
        return id;
    }
}
