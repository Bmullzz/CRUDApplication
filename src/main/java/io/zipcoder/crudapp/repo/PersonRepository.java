package io.zipcoder.crudapp.repo;

import io.zipcoder.crudapp.person.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by brianmullin on 6/15/17.
 */
public interface PersonRepository extends CrudRepository<Person, Integer>{


}
