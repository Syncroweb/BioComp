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

public class LoadDate extends AppCompatActivity {

    ListView lwPersone;

    AdView adsLDate;

    //As soon as the GUI is created
    //this function will fill the
    //GridView with the records
    //from the database, allowing, then, the user
    //to pick one and modify / load it
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_date);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsLDate = (AdView) findViewById(R.id.adsLDate);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsLDate.loadAd(adRequest);

        lwPersone = (ListView) findViewById(R.id.lwPersone);

    }
}
