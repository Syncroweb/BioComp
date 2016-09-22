package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class RegisteredDateList extends AppCompatActivity {

    ListView lwPersone;
    ArrayList<String> lista;
    ArrayAdapter<String> adapter;

    Button btnAddNewDate;

    AdView adsList;

    //As soon as the GUI is created
    //this function will fill the
    //GridView with the records
    //from the database, allowing, then, the user
    //to pick one and modify / load it
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_date_list);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsList = (AdView) findViewById(R.id.adsList);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsList.loadAd(adRequest);

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
                Intent i = new Intent(view.getContext(), Register.class);
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
