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

public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    // On définit un ArrayAdapter pour adapter la liste des personnes au ListView
    ArrayAdapter<Person> personArrayAdapter;
    public static final String PERSON = "person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listView = findViewById(R.id.listView);
        personArrayAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, DaoPerson.getAllPersons());

        listView.setAdapter(personArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long rowID) {
                Person person = DaoPerson.getAllPersons().get(index);
                Intent UserSingleActivityIntent = new Intent(getBaseContext(), UserSingleActivity.class);
                UserSingleActivityIntent.putExtra(PERSON, person);
                startActivity(UserSingleActivityIntent);
            }
        });
    }

    public void addNewPersonButtonOnClick(View view) {
        Intent mainActivityIntent = new Intent(this, AddUserActivity.class);
        startActivity(mainActivityIntent);
    }
}