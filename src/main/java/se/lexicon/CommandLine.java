package se.lexicon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.lexicon.dao.*;
import se.lexicon.models.entity.Address;
import se.lexicon.models.entity.AppUser;
import se.lexicon.models.entity.Car;
import se.lexicon.models.entity.Status;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Profile(value = "dev")
@Transactional
@Component
public class CommandLine implements CommandLineRunner {
    @Autowired
    public CommandLine(AddressDAOImpl addressDAO, AppUserDAOImpl appUserDAO, CarDAOImpl carDAO, StatusDAOImpl statusDAO, EntityManager entityManager) {
        this.addressDAO = addressDAO;
        this.appUserDAO = appUserDAO;
        this.carDAO = carDAO;
        this.statusDAO = statusDAO;
        this.entityManager = entityManager;
    }
    private final EntityManager entityManager;
    private final AppUserDAOImpl appUserDAO;
    private final AddressDAOImpl addressDAO;
    private final CarDAOImpl carDAO;
    private final StatusDAOImpl statusDAO;
    @Override
    public void run(String... args) throws Exception {
        AppUser appUser = new AppUser("bob@mail.com", "Bob", "bobbe");
        appUserDAO.save(appUser);
        entityManager.flush();
        Address address = new Address("Sydgatan", "12345 Syd", "Sydstad");
        addressDAO.save(address);
        entityManager.flush();
        appUser.setAddress(address);
        entityManager.flush();
        Car carVv = new Car("123456", "Volvo", "4Wheels");
        Car carVw = new Car("654321", "Volkswagen", "2and2Wheels");
        carDAO.save(carVv);
        carDAO.save(carVw);
        appUser.addToOwnedCars(carVv);
        appUser.addToOwnedCars(carVw);
        entityManager.flush();
        Status statusW = new Status("Well");
        Status statusD = new Status("Damaged");
        statusDAO.save(statusW);
        statusDAO.save(statusD);
        carVv.addStatusCodes(statusW);
        carVw.addStatusCodes(statusD);
        entityManager.flush();
        System.out.println(appUserDAO.findById(1));
    }
}
