package it.syncroweb.android.bio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static it.syncroweb.android.bio.DBHelper.DATABASE_NAME;

public class RegisteredDateList extends AppCompatActivity {

    ListView lwPersone;
    List<User> users = ListProvider.userList;
    CustomListAdapter adapter;
    DBHelper dbHelper;
    AdView adsList;
    FloatingActionButton fab;

    SharedPreferences mPrefs;

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

        //Creo DB
        dbHelper = new DBHelper(this);


        // Commenta questa linea se no ogni volta che parte
        // cancella il DB
        //this.deleteDatabase(DATABASE_NAME);

/*
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date newdate= null;
        try {
            newdate= df.parse("01/01/1980");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dbHelper.insertContact(String.valueOf(R.string.name), null, null, null, null, String.valueOf(R.drawable.user), newdate);
*/

        users = dbHelper.getAllContacts();

        //Creo lista personalizzata
        adapter = new CustomListAdapter(this, 0, users);
        lwPersone.setAdapter(adapter);

        //Floating Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
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

        //Create DB
        //dbHelper = new DBHelper(this);

        if(requestCode==0){
            String photo = data.getStringExtra("AVATAR");
            String name = data.getStringExtra("NAME");
            String date = data.getStringExtra("DATE");

            User user = new User(name, date, photo);

            //Insert to DB
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date newdate= null;
            try {
                newdate= df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dbHelper.insertContact(name, null,null, null, null, photo, newdate);

            users.add(user);
            adapter.notifyDataSetChanged();

        }
    }


    //Gestione del menu (3 pallini)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_date_list,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.resetList:

                dbHelper.deleteAllContact();
                adapter.notifyDataSetChanged(); //Non Immediato

                break;
        }
        return false;
    }
}
