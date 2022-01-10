package vea.home.entities;

import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String passportNumber;

    @OneToOne(mappedBy = "passport")
    private Customer customer;

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    protected Passport() {
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}
