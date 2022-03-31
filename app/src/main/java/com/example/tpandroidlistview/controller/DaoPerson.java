package com.example.tpandroidlistview.controller;

import com.example.tpandroidlistview.model.Person;

import java.util.ArrayList;

public class DaoPerson {

    // On définit une ArrayList pour stocker les personnes
    private static ArrayList<Person> Personslist = new ArrayList<>();

    public static void addPerson(Person person) {
        Personslist.add(person);
    }

    public static void removePerson(Person person) {
        Personslist.remove(person);
    }

    public static void editPerson(Person person, String nom, String prenom) {
        int index = Personslist.indexOf(person);
        Personslist.get(index).setPrenom(prenom);
        Personslist.get(index).setNom(nom);
    }

    public static boolean checkIfPersonExists(Person person) {
        for (Person p: Personslist) {
            if (p.equals(person)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Person> getAllPersons() {
        return Personslist;
    }
}
