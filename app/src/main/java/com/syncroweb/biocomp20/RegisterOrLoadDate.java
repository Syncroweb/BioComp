package com.syncroweb.biocomp20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class RegisterOrLoadDate extends AppCompatActivity {

    EditText txtName;
    Button ivPhoto;
    Button btnSave;
    NumberPicker npDay;
    NumberPicker npMonth;
    NumberPicker npYear;

    String date;
    private DBManager dbHelper = new DBManager(this);


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_or_load_date);

        txtName = (EditText) this.findViewById(R.id.txtNameSurname);
        //dpDate = (DatePicker) this.findViewById(R.id.datePicker);
        ivPhoto = (Button) this.findViewById(R.id.photoRegister);
        btnSave = (Button) this.findViewById(R.id.btnSaveChanges);

        npDay = (NumberPicker) this.findViewById(R.id.npDay);
        npMonth = (NumberPicker) findViewById(R.id.npMonth);
        npYear = (NumberPicker) findViewById(R.id.npYear);

        int year = Calendar.getInstance().get(Calendar.YEAR);

        //Set number picker
        npDay.setMinValue(1);
        npMonth.setMinValue(1);
        npYear.setMinValue(year-20);

        npDay.setMaxValue(31);
        npMonth.setMaxValue(12);
        npYear.setMaxValue(2050);


        // Construction of the given string
        date = npDay.getValue() + "/" + npMonth.getValue() + "/" + npYear.getValue();


        /* Listener of the DatePicker
        // that build the date string once
        // the user choosen the date he wants
        dpDate.init(dpDate.getYear(), dpDate.getMonth(), dpDate.getDayOfMonth(), new DatePicker.OnDateChangedListener()
        {
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                StringBuilder sb = new StringBuilder();
                date = sb.append(dayOfMonth).append("/").append(monthOfYear).append("/").append(year).toString();
            }
        });*/



        //Function that recall the DB
        //and the DBHelper function
        //'createContact' in order to
        //save the datas the user inserted
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name, surname;

                name = txtName.getText().toString().trim();

                if (name != "" && date != "") {
                    dbHelper.save(name, date, null, view.getContext());
                    finish();
                } else
                    Toast.makeText(getApplicationContext(),
                            "One or more inputs aren't right acceptable. Please, check again the datas!",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
}
