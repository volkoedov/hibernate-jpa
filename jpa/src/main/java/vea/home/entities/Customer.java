package vea.home.entities;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="passport_id")
    private Passport passport;

    public Customer(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    protected Customer() {
    }

    public Passport getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }
}
