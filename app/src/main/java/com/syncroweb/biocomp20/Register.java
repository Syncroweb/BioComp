package com.syncroweb.biocomp20;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.R.attr.checked;

public class Register extends AppCompatActivity {

    EditText txtName;
    Button avatar;
    Button btnSave;
    DatePicker dpDate;
    String name;
    String date;

    AdView adsRegister;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = (EditText) findViewById(R.id.txtNameSurname);
        btnSave = (Button) findViewById(R.id.btnSaveChanges);
        dpDate = (DatePicker) findViewById(R.id.datePicker);
        avatar = (Button) findViewById(R.id.avatar);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsRegister = (AdView) findViewById(R.id.adsRegister);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsRegister.loadAd(adRequest);


        //Setto il datePicker (problema con precisione mese APRILE = 3)
        assert dpDate != null;
        dpDate.setCalendarViewShown(false);

         //Listener of the DatePicker
        // that build the date string once
        // the user choosen the date he wants
        dpDate.init(dpDate.getYear(), dpDate.getMonth(), dpDate.getDayOfMonth(), new DatePicker.OnDateChangedListener()
        {
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                StringBuilder sb = new StringBuilder();
                date = sb.append(dayOfMonth).append("/").append(monthOfYear+1).append("/").append(year).toString();
            }
        });


        //Function that recall the DB
        //and the DBHelper function
        //'createContact' in order to
        //save the datas the user inserted
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                name = txtName.getText().toString().trim();

                if(!name.equals("") && !date.equals("")){
                    String item = name + "      |       " + date;
                    Intent i = new Intent();
                    i.putExtra("ITEM", item);
                    setResult(0, i);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(),
                            "Uno dei dati inseriti non è valido!",
                            Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Choose an avatar
    public void chooseAvatar(View view) {

        Intent i= new Intent(this, ChooseAvatar.class);
        startActivity(i);


    }
}
