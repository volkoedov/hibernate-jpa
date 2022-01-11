package vea.home.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    @ElementCollection
    @CollectionTable(name = "friend_nicknames", joinColumns = {@JoinColumn(name = "friend_id")})
    @Column(name = "nickname")
    private Set<String> nickNames = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "addresses", joinColumns = {@JoinColumn(name = "friend_id")})
    @AttributeOverride(name = "city", column = @Column(name = "address_city"))
    @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code"))
    @AttributeOverride(name = "street", column = @Column(name = "address_street"))
    private Set<Address> addresses = new HashSet<>();

    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Set<String> getNickNames() {
        return nickNames;
    }

    protected Friend() {
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nickNames=" + nickNames +
                '}';
    }

    public Set<Address> getAddresses() {
        return addresses;
    }
}
