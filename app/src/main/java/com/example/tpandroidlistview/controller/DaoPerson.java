package com.example.tpandroidlistview.controller;

import com.example.tpandroidlistview.model.Person;

import java.util.ArrayList;

public class DaoPerson {

    // On définit une ArrayList pour stocker les personnes
    private static ArrayList<Person> Personslist = new ArrayList<>();

    public static String isEligibleToAddPerson(String lastName, String firstName) {

        boolean validFirstName = (firstName.trim().length() != 0 && firstName != null && lastName.matches( "^[a-zA-Z'-]+"));
        boolean validLastName = (lastName.trim().length() != 0 && lastName != null && firstName.matches( "^[a-zA-Z'-]+"));

        if(validFirstName && validLastName) {
            return null;
        } else if(!validFirstName && !validLastName) {
            return "Invalid Firstname and Lastname.";
        } else if (!validFirstName) {
            return "Invalid First Name.";
        } else {
            return "Invalid Last Name.";
        }
    }

    public static void addPerson(Person person) {
        Personslist.add(person);
    }

    public static void removePerson(Person person) {
        Personslist.remove(person);
    }

    public static void editPerson(Person person, String firstName, String lastName) {
        int index = Personslist.indexOf(person);
        Personslist.get(index).setFirstName(firstName);
        Personslist.get(index).setLastName(lastName);
    }

    public static ArrayList<Person> getAllPersons() {
        return Personslist;
    }
}
