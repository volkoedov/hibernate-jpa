package vea.home.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guide {
    @Id
    @GeneratedValue
    private Long id;

    private String staffId;
    private String name;
    private Integer salary;



    protected Guide() {
    }

    public Guide(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }


}
