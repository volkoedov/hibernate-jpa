package vea.home.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Publisher {
    @Id
    private String code;
    @Column(name = "publisher_name")
    private String name;

    public Publisher(String code, String name) {
        this.code = code;
        this.name = name;
    }

    protected Publisher() {
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
