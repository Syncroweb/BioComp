package it.syncroweb.android.bio;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class RegisteredDateList extends AppCompatActivity {

    ListView lwPersone;
    ArrayList<String> lista;
    //ArrayAdapter<String> adapter;

    //Button btnAddNewDate;

    CustomListAdapter adapter;

    //Creo 10 possibili salvataggi
    String[] nome = new String[10];
    String[] dataNascita = new String[10];
    int[] foto = new int[10];

    DBHelper dbHelper;

    AdView adsList;

    FloatingActionButton fab;

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

        //Create DB
        dbHelper = new DBHelper(this);
        lista = dbHelper.getAllContacts();


        //Creo lista personalizzata
        adapter = new CustomListAdapter(this, lista);   //DA CAPIRE COSA PASSARE

        lwPersone = (ListView) findViewById(R.id.lwPersone);
        lwPersone.setAdapter(adapter);

        //FLOATING BUTTON
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

            //i++;    //aggiorno la posizione

            /*Riempio gli array man mano che arrivano i dati
            foto[i] = Integer.parseInt(photo);
            nome[i] = name;
            dataNascita[i] =  date;*/

            //Insert to DB
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date newdate= null;
            try {
                newdate= df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dbHelper.insertContact(name, null, null, null, null, photo, newdate);

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

                //dbHelper.deleteContact(); delete all?

                break;
        }
        return false;
    }
}
