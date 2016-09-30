package it.syncroweb.android.bio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class RegisteredDateList extends AppCompatActivity {

    ListView lwPersone;
    //ArrayList<String> lista;
    //ArrayAdapter<String> adapter;

    Button btnAddNewDate;

    int i = 0;
    CustomListAdapter adapter;

    //Creo 10 possibili salvataggi
    String[] nome = new String[10];
    String[] dataNascita = new String[10];
    int[] foto = new int[10];

    DBHelper dbHelper;

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

        //Create DB
        dbHelper = new DBHelper(this);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsList = (AdView) findViewById(R.id.adsList);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsList.loadAd(adRequest);

        nome[i] = "NOME";
        dataNascita[i] = "DATA DI NASCITA";
        foto[i] = R.drawable.user;

        //Creo lista personalizzata
        adapter = new CustomListAdapter(this, nome, dataNascita, foto);

        lwPersone = (ListView) findViewById(R.id.lwPersone);

        btnAddNewDate = (Button) findViewById(R.id.btnAddNewDate);

        /*lista= new ArrayList<>();
        lista.add("Nome     |       Data di nascita");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        */

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
            String photo = data.getStringExtra("AVATAR");
            String name = data.getStringExtra("NAME");
            String date = data.getStringExtra("DATE");

            i++;    //aggiorno la posizione

            //Riempio gli array man mano che arrivano i dati
            foto[i] = Integer.parseInt(photo);
            nome[i] = name;
            dataNascita[i] =  date;

            adapter.notifyDataSetChanged();

            /*
            lista.add(item);
            adapter.notifyDataSetChanged();
            */
        }
    }

    //Insert to DB (NON TROVA IL METODO)
    //dbHelper.execSQL("INSER INTO name VALUES ('name', 'name');");
}
