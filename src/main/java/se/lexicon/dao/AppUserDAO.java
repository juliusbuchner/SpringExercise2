package se.lexicon.dao;

import se.lexicon.models.entity.AppUser;

public interface AppUserDAO {
    AppUser findById(int id);
    AppUser save(AppUser appUser);
    AppUser delete(AppUser appUser);
}
