package vea.home.entities;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    private String zipCode;
    private String street;
    private String city;

    public Address(String zipCode, String street, String city) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
    }

    protected Address() {
    }


    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
