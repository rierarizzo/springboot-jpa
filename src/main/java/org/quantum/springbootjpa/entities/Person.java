package org.quantum.springbootjpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String name;
    @Getter
    private String lastname;
    @Setter
    @Column(name = "programming_language")
    private String programmingLanguage;
    @Embedded
    private final Audit audit = new Audit();

    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", createdAt='" + audit.getCreatedAt() + '\'' +
                ", updatedAt='" + audit.getUpdatedAt() + '\'' +
                '}';
    }

}
