package com.syncroweb.biocomp20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

public class ChooseAvatar extends AppCompatActivity {

    //GridView gridView;
    ImageView imgLY1;
    ImageView imgLY2;
    ImageView imgLY3;
    ImageView imgLY4;
    ImageView imgLY5;
    ImageView imgLY6;
    ImageView imgLY7;
    ImageView imgLY8;
    ImageView imgLY9;

   /* Integer[]avatar = {
        R.drawable.man, R.drawable.girl, R.drawable.man2, R.drawable.girl2
    }; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);

        //gridView = (GridView) findViewById(R.id.gridview);
        imgLY1 = (ImageView) findViewById(R.id.imgLY1);
        imgLY2 = (ImageView) findViewById(R.id.imgLY2);
        imgLY3 = (ImageView) findViewById(R.id.imgLY3);
        imgLY4 = (ImageView) findViewById(R.id.imgLY4);
        imgLY5 = (ImageView) findViewById(R.id.imgLY5);
        imgLY6 = (ImageView) findViewById(R.id.imgLY6);
        imgLY7 = (ImageView) findViewById(R.id.imgLY7);
        imgLY8 = (ImageView) findViewById(R.id.imgLY8);
        imgLY9 = (ImageView) findViewById(R.id.imgLY9);


        /*Create adapter to set value for grid view
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, avatar);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


            }
        }); */

    }
}
