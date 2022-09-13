package se.lexicon.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.models.entity.Status;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class StatusDAOImpl implements StatusDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Status findById(int id) {
        return entityManager.find(Status.class, id);
    }

    @Override
    public Status save(Status status) {
        entityManager.persist(status);
        return status;
    }

    @Override
    public Status delete(Status status) {
        entityManager.remove(status);
        return status;
    }
}
