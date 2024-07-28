package org.quantum.springbootjpa;

import lombok.RequiredArgsConstructor;
import org.quantum.springbootjpa.entities.Person;
import org.quantum.springbootjpa.repositories.IPersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    private final IPersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        /*list();
        findOne();
        create();*/
        //update();
        create();
    }

    @Transactional(readOnly = true)
    public void list() {
        var persons = this.personRepository.findPersonByProgrammingLanguage("Java");
        persons.forEach(System.out::println);

        var personData = this.personRepository.findPersonData();
        personData.forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
    }

    @Transactional(readOnly = true)
    public void findOne() {
        System.out.println("findOne");
        this.personRepository.findOne(1L).ifPresent(System.out::println);

        System.out.println("findOneByName");
        this.personRepository.findOneByName("Keneth").ifPresent(System.out::println);
    }

    @Transactional
    public void create() {
        var scanner = new Scanner(System.in);
        var name = scanner.next();
        var lastname = scanner.next();
        var programmingLang = scanner.next();
        scanner.close();

        var person = new Person(null, name, lastname, programmingLang);
        person = this.personRepository.save(person);
        System.out.println(person);
    }

    @Transactional
    public void update() {
        var scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del usuario:");
        var id = scanner.nextLong();

        var optionalPerson = this.personRepository.findById(id);
        optionalPerson.ifPresent(person -> {
            System.out.println("Ingrese el lenguaje de programación:");
            String programmingLang = scanner.next();
            person.setProgrammingLanguage(programmingLang);

            this.personRepository.save(person);
        });

        scanner.close();
    }

    @Transactional
    public void delete() {
        var scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del usuario:");
        var id = scanner.nextLong();
        scanner.close();

        this.personRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        var scanner = new Scanner(System.in);
        var id = scanner.nextLong();
        scanner.close();

        System.out.println("Nombre: " + this.personRepository.getNameById(id));
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        var personsRegs = personRepository.findAllMixPerson();

        personsRegs.forEach(reg -> {
            System.out.println("programmingLang: " + reg[1]);
            System.out.println("person: " + reg[0]);
        });

        var persons = personRepository.findAllClassPerson();
        persons.forEach(person -> {
            System.out.println("name: " + person.getName());
            System.out.println("lastname: " + person.getLastname());
        });

        var personsDto = personRepository.findAllPersonDto();
        personsDto.forEach(System.out::println);
    }

}
