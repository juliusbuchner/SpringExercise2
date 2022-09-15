package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.models.entity.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class CarDAOImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarDAOImpl carDAO;

    private Car testObject;



    @BeforeEach
    void setUp(){
        List<Car> cars = new ArrayList<>(
                Arrays.asList(
                        new Car("123456", "Volvo", "4Wheels"),
                        new Car("654321", "Volkswagen", "2and2Wheels")
                )
        );
        List<Car> persistedCars = cars.stream()
                .map(entityManager::persist).toList();
        testObject = persistedCars.get(0);
    }

    @Test
    void findById() {
        Car foundCars = carDAO.findById(testObject.getCarId());
        assertNotNull(foundCars);
    }

    @Test
    void save() {
        Car car = new Car("8462159", "Brandbil", "flerahjul");
        Car persistedCars = carDAO.save(car);

        assertNotNull(persistedCars);
        assertNotEquals(0,persistedCars.getCarId());
        assertNotNull(entityManager.find(Car.class, persistedCars.getCarId()));
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(Car.class, testObject.getCarId()));
        carDAO.delete(testObject);
        assertNull(entityManager.find(Car.class, testObject.getCarId()));
    }
}