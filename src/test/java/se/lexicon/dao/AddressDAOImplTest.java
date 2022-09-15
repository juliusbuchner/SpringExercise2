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
import se.lexicon.models.entity.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class AddressDAOImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressDAOImpl addressDAO;

    private Address testObject;



    @BeforeEach
    void setUp(){
        List<Address> addresses = new ArrayList<>(
                Arrays.asList(
                        new Address("Sydgatan", "12345 Syd", "Sydstad"),
                        new Address("Nordgatan", "54321 Nord", "Nordstad")
                )
        );
        List<Address> persistedAddress = addresses.stream()
                .map(entityManager::persist).toList();
        testObject = persistedAddress.get(0);
    }

    @Test
    void findById() {
        Address foundAddress = addressDAO.findById(testObject.getAddressId());
        assertNotNull(foundAddress);
    }

    @Test
    void save() {
        Address address = new Address("Vestgatan", "51342 Vest", "Veststad");
        Address persistedAddress = addressDAO.save(address);

        assertNotNull(persistedAddress);
        assertNotEquals(0,persistedAddress.getAddressId());
        assertNotNull(entityManager.find(Address.class, persistedAddress.getAddressId()));
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(Address.class, testObject.getAddressId()));
        addressDAO.delete(testObject);
        assertNull(entityManager.find(Address.class, testObject.getAddressId()));
    }
}