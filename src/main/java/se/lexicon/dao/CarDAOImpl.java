package se.lexicon.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.models.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class CarDAOImpl implements CarDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Car findById(int id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public Car save(Car car) {
        entityManager.persist(car);
        return car;
    }

    @Override
    public Car delete(Car car) {
        entityManager.remove(car);
        return car;
    }
}
