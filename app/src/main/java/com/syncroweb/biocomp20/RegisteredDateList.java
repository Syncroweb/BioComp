package com.syncroweb.biocomp20;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RegisteredDateList extends AppCompatActivity {

    ListView lwPersone;
    ArrayList<String> lista;
    ArrayAdapter<String> adapter;

    Button btnAddNewDate;

    //As soon as the GUI is created
    //this function will fill the
    //GridView with the records
    //from the database, allowing, then, the user
    //to pick one and modify / load it
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_date_list);

        lwPersone = (ListView) findViewById(R.id.lwPersone);
        btnAddNewDate = (Button) findViewById(R.id.btnAddNewDate);
        lista= new ArrayList<>();

        lista.add("Nome     |       Data di nascita");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        lwPersone.setAdapter(adapter);

       //Start Activity Register
        btnAddNewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterOrLoadDate.class);
                startActivityForResult(i, 0);
            }
        });
    }

    // Call Back method to get the Strings form other Activity
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0){
            String item = data.getStringExtra("ITEM");
            lista.add(item);
            adapter.notifyDataSetChanged();
        }
    }
}
