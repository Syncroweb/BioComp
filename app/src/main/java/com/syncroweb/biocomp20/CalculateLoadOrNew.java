package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CalculateLoadOrNew extends AppCompatActivity {

    final int DATE_LISTS_ID = 1032;
    final int CHOOSE_A_DATE_ID = 1342;

    final int RESULT_OK_LIST = 1618;
    final int RESULT_OK_DATE = 1588;

    Button btnNewDate;
    Button btnLoadDate;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_load_or_new);

        btnNewDate = (Button)findViewById(R.id.btnNewDate);
        btnLoadDate = (Button)findViewById(R.id.btnLoadDate);

        // Start the activity that allow the user
        // to choose a saved profile.
        btnLoadDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisteredDateList.class);
                startActivityForResult(i, DATE_LISTS_ID);
            }
        });


        // Start the activity that allow the user
        // to choose a single date
        btnNewDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ChooseDate.class);
                startActivityForResult(i, CHOOSE_A_DATE_ID);
            }
        });
    }


    // Receive datas from the Struct in the first case.
    // Receive only a date in the second case.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            // Put Extras in order to allow the calculate activity
            // to receive all the datas and elaborate 'em.
            case DATE_LISTS_ID:
                if(resultCode == RESULT_OK)
                {
                    Intent i = new Intent();
                    i.putExtra("date", data.getStringExtra("date"));
                    i.putExtra("name", data.getStringExtra("name"));
                    setResult(RESULT_OK_LIST, i);
                    finish();
                }
                else
                    Toast.makeText(     getApplicationContext(),
                            "Something went wrong. Couldn't load the selected Profile!",
                            Toast.LENGTH_SHORT ).show();
                break;

            // Put a single extra with the date in order
            // to allow the calculate activity to elaborate it.
            case CHOOSE_A_DATE_ID:
                if(resultCode == RESULT_OK)
                {
                    Intent i = new Intent();
                    i.putExtra("date", data.getStringExtra("DATE"));
                    setResult(RESULT_OK_DATE, i);
                    finish();
                }
                else
                    Toast.makeText(     getApplicationContext(),
                            "Something went wrong. Couldn't load the selected Date!",
                            Toast.LENGTH_SHORT ).show();
                break;
        }
    }
}
