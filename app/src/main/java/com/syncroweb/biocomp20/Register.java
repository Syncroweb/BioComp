package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText txtName;
    //Button ivPhoto;
    Button btnSave;
    DatePicker dpDate;


    String name;
    String date;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = (EditText) findViewById(R.id.txtNameSurname);
        //ivPhoto = (Button) findViewById(R.id.photoRegister);
        btnSave = (Button) findViewById(R.id.btnSaveChanges);
        dpDate = (DatePicker) findViewById(R.id.datePicker);


        //Setto il datePicker (problema con precisione mese APRILE = 3)
        assert dpDate != null;
        dpDate.setCalendarViewShown(false);
        dpDate.setMinDate(1940);
        dpDate.setMaxDate(System.currentTimeMillis());

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


}
