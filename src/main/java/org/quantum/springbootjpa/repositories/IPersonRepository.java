package org.quantum.springbootjpa.repositories;

import org.quantum.springbootjpa.dto.PersonDto;
import org.quantum.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository extends CrudRepository<Person, Long> {

    @Query("select concat(p.name, ' ', p.lastname) from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneByName(String name);

    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person> findPersonByProgrammingLanguage(String programmingLanguage);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> findPersonData();

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllClassPerson();

    @Query("select new org.quantum.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

}
