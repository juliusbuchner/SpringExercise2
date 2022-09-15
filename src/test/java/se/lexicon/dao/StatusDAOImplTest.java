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
import se.lexicon.models.entity.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class StatusDAOImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StatusDAOImpl statusDAO;

    private Status testObject;



    @BeforeEach
    void setUp(){
        List<Status> statuses = new ArrayList<> (
            Arrays.asList(
                    new Status("Well"),
                    new Status("Damaged")
            )
        );
        List<Status> persistedStatuses = statuses.stream()
                .map(entityManager::persist).toList();
        testObject = persistedStatuses.get(0);
    }

    @Test
    void findById() {
        Status foundStatus = statusDAO.findById(testObject.getStatusId());
        assertNotNull(foundStatus);
    }

    @Test
    void save() {
        Status status = new Status("Decent");
        Status persistedStatus = statusDAO.save(status);

        assertNotNull(persistedStatus);
        assertNotEquals(0,persistedStatus.getStatusId());
        assertNotNull(entityManager.find(Status.class, persistedStatus.getStatusId()));
    }

    @Test
    void delete() {
        assertNotNull(entityManager.find(Status.class, testObject.getStatusId()));
        statusDAO.delete(testObject);
        assertNull(entityManager.find(Status.class, testObject.getStatusId()));
    }
}