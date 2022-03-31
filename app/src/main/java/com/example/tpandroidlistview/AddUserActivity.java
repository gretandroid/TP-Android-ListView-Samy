package com.example.tpandroidlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpandroidlistview.controller.DaoPerson;
import com.example.tpandroidlistview.model.Person;

public class AddUserActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastNameEditText = findViewById(R.id.nomEditText);
        firstNameEditText = findViewById(R.id.prenomEditText);
    }

    public void addButtonOnClick(View view) {
        String firstName = String.valueOf(firstNameEditText.getText().toString().charAt(0)).toUpperCase()+firstNameEditText.getText().toString().substring(1).toLowerCase();
        String lastName = lastNameEditText.getText().toString().toUpperCase();
        String error = DaoPerson.isEligibleToAddPerson(firstName, lastName);

        if(error == null) {
            Person newPerson = new Person(firstName, lastName);
            if(!DaoPerson.getAllPersons().contains(newPerson)) {
                DaoPerson.addPerson(newPerson);
                Toast.makeText(getBaseContext(), newPerson.getFirstName()+" "+newPerson.getLastName()+" ajouté(e) !", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), newPerson.getFirstName()+" "+newPerson.getLastName()+" existe déjà !", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
        }
    }

    public void backToUsersListButtonOnClick(View view) {
        finish();
    }
}