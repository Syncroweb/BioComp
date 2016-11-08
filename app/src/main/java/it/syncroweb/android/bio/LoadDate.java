package it.syncroweb.android.bio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class LoadDate extends AppCompatActivity {

    ListView list;
    AdView adsLDate;
    List<User> users = ListProvider.userList;
    DBHelper dbHelper;
    CustomListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_date);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsLDate = (AdView) findViewById(R.id.adsLDate);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsLDate.loadAd(adRequest);

        list = (ListView) findViewById(R.id.list);

        dbHelper = new DBHelper(this);
        users = dbHelper.getAllContacts();

        adapter = new CustomListAdapter(this, 0, users);    //Problema Visualizzazione data!
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selected = (String) parent.getItemAtPosition(position);

            }
        });

    }

}
