package org.quantum.springbootjpa.repositories;

import org.quantum.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneByName(String name);

    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person> findPersonByProgrammingLanguage(String programmingLanguage);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> findPersonData();

}
