package com.example.tpandroidlistview.model;

import android.util.Log;

import com.example.tpandroidlistview.controller.DaoPerson;

import java.io.Serializable;
import java.util.Objects;


// On serialise l'objet Person, ça veux dire qu'il est converti en chaine pour être envoyé à l'autre Activity, qui va le déserialiser en objet.
public class Person implements Serializable {

    // Attributes
    private static int idCounter = 1;
    private int id;
    private String nom, prenom;


    // Constructors
    public Person(String nom, String prenom) {
        this.id = idCounter;
        idCounter++;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return id +" - "+nom+" "+prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getNom(), person.getNom()) && Objects.equals(getPrenom(), person.getPrenom());
    }

}
