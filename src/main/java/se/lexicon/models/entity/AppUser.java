package se.lexicon.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false, unique = true)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
//    @JoinColumn(name = "owner")
    private List<Car> ownedCars = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(int id, String email, String name, String password, Address address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public AppUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Car> getOwnedCars() {
        return ownedCars;
    }

    public void addToOwnedCars(Car ownedCars) {
        this.ownedCars.add(ownedCars);
        ownedCars.setOwner(this);
    }
    public void removeFromOwnedCars(Car ownedCars){
        ownedCars.setOwner(null);
        this.ownedCars.remove(ownedCars);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(email, appUser.email) && Objects.equals(name, appUser.name) && Objects.equals(password, appUser.password) && Objects.equals(address, appUser.address) && Objects.equals(ownedCars, appUser.ownedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, address, ownedCars);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", ownedCars=" + ownedCars +
                '}';
    }
}
