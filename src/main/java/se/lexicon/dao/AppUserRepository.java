package se.lexicon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.models.entity.AppUser;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    @Query("SELECT user FROM AppUser user WHERE user.email = :email")
    AppUser findAppUserByEmail(@Param("email") String email);

    @Query("SELECT user FROM AppUser user WHERE user.email = :email AND user.password = :password")
    AppUser findAppUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT users FROM AppUser users WHERE users.name = :name")
    List<AppUser> findAppUsersByName(@Param("name") String name);

    @Query("SELECT users FROM AppUser users WHERE users.address = :address")
    List<AppUser> findAppUsersByAddress(@Param("address") String address);

    @Query("SELECT users FROM AppUser users WHERE users.address.city = :city")
    List<AppUser> findAppUsersByAddress_City(@Param("city") String city);
}
