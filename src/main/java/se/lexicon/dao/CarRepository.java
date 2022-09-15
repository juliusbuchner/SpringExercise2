package se.lexicon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.models.entity.Car;
import se.lexicon.models.entity.Status;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query("SELECT car FROM Car car WHERE car.regNumber = :regNumber")
    Car findCarByRegNumber(@Param("regNumber") String regNumber);

    @Query("SELECT car FROM Car car WHERE car.statusCodes = :status")
    List<Car> findCarsByStatusCodes(@Param("status") Status status);

    @Query("SELECT car FROM Car car WHERE car.regDate < :date")
    List<Car> findCarsByRegDateBefore(@Param("date")LocalDate date);

    @Query("SELECT car FROM Car car WHERE car.regDate > :date")
    List<Car> findCarsByRegDateAfter(@Param("date")LocalDate date);

    @Query("SELECT car FROM Car car WHERE car.regDate BETWEEN :date1 AND :date2")
    List<Car> findCarsByRegDateBetween(@Param("date1")LocalDate date1, @Param("date2")LocalDate date2);
}
