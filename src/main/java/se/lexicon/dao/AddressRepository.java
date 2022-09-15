package se.lexicon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.models.entity.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    @Query("SELECT address FROM Address address WHERE address.city = :city")
    List<Address> findAddressesByCity(@Param("city") String city);
}
