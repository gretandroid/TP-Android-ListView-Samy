package com.example.tpandroidlistview.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;


// On serialise l'objet Person, ça veux dire qu'il est converti en chaine pour être envoyé à l'autre Activity, qui va le déserialiser en objet.
public class Person implements Serializable {

    // Attributes
    private static int idCounter = 1;
    private int id;
    private String firstName, lastName;


    // Constructors
    public Person(String firstName, String lastName) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return id +" - "+ firstName +" "+ lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getFirstName(), person.getFirstName());
    }
}
