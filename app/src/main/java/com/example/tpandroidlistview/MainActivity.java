package com.example.tpandroidlistview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpandroidlistview.controller.DaoPerson;
import com.example.tpandroidlistview.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_PERSONS = "personnes";
    EditText nomEditText, prenomEditText;
    Person selectedPerson;

    // On déclare un lancer pour un appel préalablement déclaré (intent) pour démarrer une processus d'éxécution d'un ActivityResultContract.
    ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);

        // On déclare un lancer pour un appel préalablement déclaré (intent) pour démarrer une processus d'éxécution d'un ActivityResultContract.
        intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult activityResult) {
                        if (activityResult.getResultCode() == Activity.RESULT_OK) {
                            Intent data = activityResult.getData();
                            selectedPerson = (Person) data.getSerializableExtra(ListViewActivity.PERSON);
                            nomEditText.setText(selectedPerson.getNom());
                            prenomEditText.setText(selectedPerson.getPrenom());
                        }
                    }
                });
    }


    public void addButtonOnClick(View view) {

        // On récupère la liste des personnes et on créé une nouvelle Personne
        String nom = nomEditText.getText().toString();
        String prenom = prenomEditText.getText().toString();
        Person person = new Person(nom, prenom);

        // On ajoute la personne si elle n'existe pas déjà
        if(!DaoPerson.checkIfPersonExists(person)) {
            DaoPerson.addPerson(person);
            Toast.makeText(getBaseContext(), person.getPrenom()+" "+person.getNom()+" ajoutée !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), prenom+" "+nom+" existe déjà !", Toast.LENGTH_LONG).show();
        }
    }


    public void editButtonOnClick(View view) {

        if(selectedPerson != null) {
            String nom = nomEditText.getText().toString();
            String prenom = prenomEditText.getText().toString();
            DaoPerson.editPerson(selectedPerson, nom, prenom);
            Toast.makeText(getBaseContext(), prenom + " " + nom + " mis-à-jour !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Veuillez selectionner une Personne !", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteButtonOnClick(View view) {

        String nom = nomEditText.getText().toString();
        String prenom = prenomEditText.getText().toString();
        Person person = new Person(nom, prenom);

        if(DaoPerson.checkIfPersonExists(person)) {
            DaoPerson.removePerson(person);
            nomEditText.setText("");
            prenomEditText.setText("");
            selectedPerson = null;
            Toast.makeText(getBaseContext(), prenom+" "+nom+" a été supprimé !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Veuillez selectionner une Personne !", Toast.LENGTH_LONG).show();
        }
    }

    public void displayListButtonOnClick(View view) {
        // On va créer l'intent pour démarrer LstViewActivity
        Intent intent = new Intent(this, ListViewActivity.class);
        // On stocke l'ArrayList de personne à envoyer à ListViewActivity
        intent.putExtra(MainActivity.LIST_PERSONS, DaoPerson.getAllPersons());
        // On lance l'Activity
        intentActivityResultLauncher.launch(intent);
    }
}