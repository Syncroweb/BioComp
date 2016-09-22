package com.syncroweb.biocomp20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class OtherApps extends AppCompatActivity {

    private Button imgOne;
    private Button imgTwo;
    private Button imgSyncroweb;

    AdView adsOther;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_apps);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsOther = (AdView) findViewById(R.id.adsOther);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsOther.loadAd(adRequest);

        imgOne = (Button)findViewById(R.id.btnImgOne);
        imgTwo = (Button)findViewById(R.id.btnImgTwo);
        imgSyncroweb = (Button)findViewById(R.id.btnSyncroweb);

        //Start a new Google Market activity
        //that shows up the beclicked app's logo
        imgOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String tag;
                tag = imgOne.getTag().toString();
                intent.setData(Uri.parse("market://details?id=" + tag));
                startActivity(intent);
            }
        });

        //Start a new Google Market activity
        //that shows up the beclicked app's logo
        imgTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String tag;
                tag = imgTwo.getTag().toString();
                intent.setData(Uri.parse("market://details?id=" + tag));
                startActivity(intent);            }
        });

        imgSyncroweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri= Uri.parse("http://www.syncroweb.it");
                Intent i= new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }

    //Create a new Google Market activity
    //that shows up all the apps made by
    //Syncroweb
    public void startFactoryPage()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://search?q=pub:syncroweb"));
        startActivity(intent);
    }
}
