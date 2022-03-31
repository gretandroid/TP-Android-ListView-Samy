package com.example.tpandroidlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tpandroidlistview.controller.DaoPerson;
import com.example.tpandroidlistview.model.Person;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    // On définit un ArrayAdapter pour adapter la liste des personnes au ListView
    ArrayAdapter<Person> personArrayAdapter;
    public static final String PERSON = "personne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listView);

        // On récupère l'Intent envoyé par la MainActivity
        Intent intent = getIntent();

        // On récupère l'ArrayList de Personnes "personnesList"
        ArrayList<Person> personnes = (ArrayList<Person>) intent.getSerializableExtra(MainActivity.LIST_PERSONS);

        personArrayAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, personnes);

        listView.setAdapter(personArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long rowID) {
                Person person = DaoPerson.getAllPersons().get(index);
                // On créé un Intent
                Intent intent2 = new Intent();
                intent2.putExtra(PERSON, person);

                // On renvoie un code à la MainActivity pour lui indiquer que tout s'est bien passé
                setResult(RESULT_OK, intent2);

                // On ferme la listViewActivity
                finish();
            }
        });
    }
}