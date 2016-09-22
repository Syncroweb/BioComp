package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainMenu extends AppCompatActivity {

    Button btnCalculate;
    AdView adsMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        // /Banner
        adsMain = (AdView) findViewById(R.id.adsMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsMain.loadAd(adRequest);

        //Start the activity that calculate
        //the BioCompatibility
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Calculate.class);
                startActivity(intent);
            }
        });

    }

    //Gestione del menu (3 pallini)

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.menu1:

                //Start the activity that show
                //all the saved people's datas
                Intent menu1 = new Intent(this, RegisteredDateList.class);
                startActivity(menu1);

                break;

            case R.id.menu2:

                //Start the activity that contains
                //the infos about the BioCompatibility
                Intent menu2 = new Intent(this, Infos.class);
                startActivity(menu2);

                break;

            case R.id.menu3:

                //Start the activity that contains
                //the links to the other syncroweb apps
                //or to the syncroweb's app page
                Intent menu3 = new Intent(this, OtherApps.class);
                startActivity(menu3);

                break;
        }
        return false;
    }
}

