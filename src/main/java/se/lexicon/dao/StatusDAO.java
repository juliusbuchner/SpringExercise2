package se.lexicon.dao;

import se.lexicon.models.entity.Status;

public interface StatusDAO {
    Status findById(int id);
    Status save(Status status);
    Status delete(Status status);
}
