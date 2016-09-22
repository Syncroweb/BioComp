package com.syncroweb.biocomp20;

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

public class Infos extends AppCompatActivity {

    TextView text;
    AdView adsInfo;

    AssetManager assetManager;

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

        assetManager = getAssets();

        text.setText(readFile("bioritmi.txt"));

    }

    //Function that read the file in Assets folder
    //and return the content
    public String readFile(String path)
    {
        String contents = "";
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = assetManager.open(path);
            reader = new BufferedReader(new InputStreamReader(is));
            contents = reader.readLine();
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents += '\n' + line;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return contents;

    }
}
