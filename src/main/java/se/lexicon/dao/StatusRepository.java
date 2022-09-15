package se.lexicon.dao;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.models.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
}
