package it.syncroweb.android.bio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class Infos extends AppCompatActivity {

    TextView text;
    AdView adsInfo;

    //Read the file from the root.
    //The file contains all the infos
    //about the BioCompatibility.
    //Once read, all the text will be
    //displayed into a TextView
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        text = (TextView) findViewById(R.id.txtBioritmi);

        //Banner
        adsInfo = (AdView) findViewById(R.id.adsInfo);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsInfo.loadAd(adRequest);


    }
}
