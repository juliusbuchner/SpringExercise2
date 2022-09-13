package se.lexicon.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.models.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class AppUserDAOImpl implements AppUserDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class, id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser delete(AppUser appUser) {
        entityManager.remove(appUser);
        return appUser;
    }
}
