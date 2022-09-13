package se.lexicon.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    @Column(length = 100, nullable = false, unique = true)
    private String regNumber;
    @Column(length = 100, nullable = false)
    private String brand;
    @Column(length = 100, nullable = false)
    private String model;
    @Column(name = "register_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate regDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "appUser")
    private AppUser owner;

    @ManyToMany
    private List<Status> statusCodes = new ArrayList<>();

    public Car() {
    }

    public Car(int carId, String regNumber, String brand, String model, LocalDate regDate, AppUser owner, List<Status> statusCodes) {
        this.carId = carId;
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
        this.owner = owner;
        this.statusCodes = statusCodes;
    }

    public Car(String regNumber, String brand, String model) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
    }

    public int getCarId() {
        return carId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser appUser) {
        this.owner = appUser;
    }

    public List<Status> getStatusCodes() {
        return statusCodes;
    }

    public void addStatusCodes(Status statusCode) {
        this.statusCodes.add(statusCode);
        statusCode.getCars().add(this);
    }
    public void removeStatusCodes(Status statusCode){
        statusCode.getCars().remove(this);
        this.statusCodes.remove(statusCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(regNumber, car.regNumber) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(regDate, car.regDate) && Objects.equals(owner, car.owner) && Objects.equals(statusCodes, car.statusCodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, regNumber, brand, model, regDate, owner, statusCodes);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regDate=" + regDate +
//                ", owner=" + owner +
                ", statusCodes=" + statusCodes +
                '}';
    }
}