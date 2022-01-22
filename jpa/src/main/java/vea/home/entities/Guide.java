package vea.home.entities;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@BatchSize(size = 3)
public class Guide {
    @Id
    @GeneratedValue
    private Long id;

    private String staffId;
    private String name;
    private Integer salary;

    public Set<Student> getStudents() {
        return students;
    }

    @OneToMany(mappedBy = "guide", cascade = {CascadeType.MERGE})
    private Set<Student> students = new HashSet<>();

    protected Guide() {
    }

    public Guide(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public void addStudent(Student student) {
        student.setGuide(this);
        students.add(student);

    }

    public void removeStudent(Student student){
        students.remove(student);
        student.setGuide(null);
    }

    public String getName() {
        return name;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
