package se.lexicon.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(length = 100, nullable = false)
    private String statusCode;
    @ManyToMany(mappedBy = "statusCodes")
    private List<Car> cars = new ArrayList<>();

    public Status() {
    }

    public Status(int statusId, String statusCode, List<Car> cars) {
        this.statusId = statusId;
        this.statusCode = statusCode;
        this.cars = cars;
    }

    public Status(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
        car.getStatusCodes().add(this);
    }
    public void removeCar(Car car){
        car.getStatusCodes().remove(this);
        this.cars.remove(car);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId && Objects.equals(statusCode, status.statusCode) && Objects.equals(cars, status.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusCode, cars);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusCode=" + statusCode +
//                ", statusCode='" + statusCode + '\'' +
//                ", cars=" + cars +
                '}';
    }
}
