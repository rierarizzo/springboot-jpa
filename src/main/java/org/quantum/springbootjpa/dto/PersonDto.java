package org.quantum.springbootjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * PersonDto representa un objeto de transferencia con la información de una persona.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class PersonDto {
    /**
     * Nombre de la persona.
     */
    private String name;
    /**
     * Apellido de la persona.
     */
    private String lastname;
}
