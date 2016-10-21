package it.syncroweb.android.bio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class LoadDate extends AppCompatActivity {

    ListView lwPersone;

    AdView adsLDate;

    LayoutInflater inflater;

    RelativeLayout parentLayout;

    //As soon as the GUI is created
    //this function will fill the
    //GridView with the records
    //from the database, allowing, then, the user
    //to pick one and modify / load it
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_date);
/*
        //Inflater Layout lwPersone list into activity_load_date (no work)
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = inflater.inflate(R.layout.activity_registered_date_list,
                (ViewGroup) findViewById(R.id.lwPersone));
        parentLayout = (RelativeLayout) findViewById(R.id.parent);
        assert parentLayout != null;
        parentLayout.addView(childLayout);
*/
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsLDate = (AdView) findViewById(R.id.adsLDate);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsLDate.loadAd(adRequest);

        lwPersone = (ListView) findViewById(R.id.lwPersone);

        //CARICARE IL DB CREATO SU REGISTER DATE LIST
    }
}
