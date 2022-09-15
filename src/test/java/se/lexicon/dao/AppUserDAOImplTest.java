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
import se.lexicon.models.entity.AppUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class AppUserDAOImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserDAOImpl appUserDAO;

    private AppUser testObject;



    @BeforeEach
    void setUp(){
        List<AppUser> appUsers = new ArrayList<>(
                Arrays.asList(
                        new AppUser("bob@mail.com", "Bob", "bobbe"),
                        new AppUser("bleb@mail.com", "Bleb", "blobben")
                )
        );
        List<AppUser> persistedAppUser = appUsers.stream()
                .map(entityManager::persist).toList();
        testObject = persistedAppUser.get(0);
    }

    @Test
    void findById() {
        AppUser foundAppUser = appUserDAO.findById(testObject.getId());
        assertNotNull(foundAppUser);
    }

    @Test
    void save() {
        AppUser appUser = new AppUser("blab@mail.com", "Blab", "blebb");
        AppUser persistedAppUser = appUserDAO.save(appUser);

        assertNotNull(persistedAppUser);
        assertNotEquals(0,persistedAppUser.getId());
        assertNotNull(entityManager.find(AppUser.class, persistedAppUser.getId()));
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(AppUser.class, testObject.getId()));
        appUserDAO.delete(testObject);
        assertNull(entityManager.find(AppUser.class, testObject.getId()));
    }
}