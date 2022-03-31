package com.example.tpandroidlistview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpandroidlistview.controller.DaoPerson;
import com.example.tpandroidlistview.model.Person;

public class UserSingleActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText;

    Person selectedPerson;
    // On déclare un lancer pour un appel préalablement déclaré (intent) pour démarrer une processus d'éxécution d'un ActivityResultContract.
    ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_single);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);

        Intent data = getIntent();
        selectedPerson = (Person) data.getSerializableExtra("person");
        firstNameEditText.setText(selectedPerson.getFirstName());
        lastNameEditText.setText(selectedPerson.getLastName());
    }


    public void editButtonOnClick(View view) {
        String firstName = String.valueOf(firstNameEditText.getText().toString().charAt(0)).toUpperCase()+firstNameEditText.getText().toString().substring(1).toLowerCase();
        String lastName = lastNameEditText.getText().toString().toUpperCase();
        String error = DaoPerson.isEligibleToAddPerson(firstName, lastName);

        if(error == null) {
            DaoPerson.editPerson(selectedPerson, firstName, lastName);
            selectedPerson.setFirstName(firstName);
            selectedPerson.setLastName(lastName);
            firstNameEditText.setText(firstName);
            lastNameEditText.setText(lastName);
            Toast.makeText(getBaseContext(), selectedPerson.getFirstName()+" "+selectedPerson.getLastName()+" mis-à-jour !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
        }
    }

    public void deleteButtonOnClick(View view) {
        int index = DaoPerson.getAllPersons().indexOf(selectedPerson);
        DaoPerson.removePerson(DaoPerson.getAllPersons().get(index));
        Toast.makeText(getBaseContext(), selectedPerson.getFirstName()+" "+selectedPerson.getLastName()+" a été supprimé !", Toast.LENGTH_LONG).show();
        finish();
    }

    public void backToUsersListButton(View view) {
        finish();
    }
}