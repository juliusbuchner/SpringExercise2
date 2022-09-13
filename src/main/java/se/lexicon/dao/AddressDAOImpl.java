package se.lexicon.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.models.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class AddressDAOImpl implements AddressDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Address findById(int id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public Address delete(Address address) {
        entityManager.remove(address);
        return address;
    }
}
