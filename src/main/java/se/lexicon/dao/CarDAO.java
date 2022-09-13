package se.lexicon.dao;

import se.lexicon.models.entity.Car;

public interface CarDAO {
    Car findById(int id);
    Car save(Car car);
    Car delete(Car car);
}
