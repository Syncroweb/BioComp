package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button btnCalculate;
    Button btnDateList;
    Button btnInfo;
    Button btnOtherApps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnDateList = (Button) findViewById(R.id.btnDatesList);
        btnInfo = (Button) findViewById(R.id.btnInfos);
        btnOtherApps = (Button) findViewById(R.id.btnOtherApps);

        //Start the activity that calculate
        //the BioCompatibility
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Calculate.class);
                startActivity(intent);
            }
        });

        //Start the activity that show
        //all the saved people's datas
        btnDateList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisteredDateList.class);
                startActivity(intent);
            }
        });

        //Start the activity that contains
        //the infos about the BioCompatibility
        btnInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Infos.class);
                startActivity(intent);
            }
        });

        //Start the activity that contains
        //the links to the other syncroweb apps
        //or to the syncroweb's app page
        btnOtherApps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OtherApps.class);
                startActivity(intent);
            }
        });
    }

}
