package se.lexicon.dao;

import se.lexicon.models.entity.Address;

public interface AddressDAO {
    Address findById(int id);
    Address save(Address address);
    Address delete(Address address);
}
