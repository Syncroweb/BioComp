package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ChooseAvatar extends AppCompatActivity {

    ImageView imgLY1;
    ImageView imgLY2;
    ImageView imgLY3;
    ImageView imgLY4;
    ImageView imgLY5;
    ImageView imgLY6;
    ImageView imgLY7;
    ImageView imgLY8;
    ImageView imgLY9;

    String avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);

        imgLY1 = (ImageView) findViewById(R.id.imgLY1);
        imgLY2 = (ImageView) findViewById(R.id.imgLY2);
        imgLY3 = (ImageView) findViewById(R.id.imgLY3);
        imgLY4 = (ImageView) findViewById(R.id.imgLY4);
        imgLY5 = (ImageView) findViewById(R.id.imgLY5);
        imgLY6 = (ImageView) findViewById(R.id.imgLY6);
        imgLY7 = (ImageView) findViewById(R.id.imgLY7);
        imgLY8 = (ImageView) findViewById(R.id.imgLY8);
        imgLY9 = (ImageView) findViewById(R.id.imgLY9);

    }

    //NON FA QUEL CHE DEVE FARE... TROVARE ALTRO MODO
        public void press(View v) {
        switch (v.getId()) {
            case R.id.imgLY1:
                 Toast t= Toast.makeText(ChooseAvatar.this,
                         "CLICK",
                         Toast.LENGTH_SHORT);
                        t.show();
                avatar = String.valueOf(imgLY1.getResources());
                //Intent i = new Intent();
                //i.putExtra("AVATAR", avatar);
                //setResult(0, i);
                //finish();
                break;

            case R.id.imgLY2:
                avatar = String.valueOf(imgLY2.getResources());
                //Intent i2 = new Intent();
                //i2.putExtra("AVATAR", avatar);
                //setResult(0, i2);
                //finish();
                break;

            case R.id.imgLY3:
                avatar = String.valueOf(R.id.imgLY3);
                //Intent i3 = new Intent();
                //i3.putExtra("AVATAR", avatar);
                //setResult(0, i3);
                //finish();
                break;

            case R.id.imgLY4:
                avatar = String.valueOf(R.id.imgLY4);
                //Intent i4 = new Intent();
                //i4.putExtra("AVATAR", avatar);
                //setResult(0, i4);
                //finish();
                break;

            case R.id.imgLY5:
                avatar = String.valueOf(R.id.imgLY5);
                //Intent i5 = new Intent();
                //i5.putExtra("AVATAR", avatar);
                //setResult(0, i5);
                //finish();
                break;

            case R.id.imgLY6:
                avatar = String.valueOf(R.id.imgLY6);
                //Intent i6 = new Intent();
                //i6.putExtra("AVATAR", avatar);
                //setResult(0, i6);
                //finish();
                break;

            case R.id.imgLY7:
                avatar = String.valueOf(R.id.imgLY7);
                //Intent i7 = new Intent();
                //i7.putExtra("AVATAR", avatar);
                //setResult(0, i7);
                //finish();
                break;

            case R.id.imgLY8:
                avatar = String.valueOf(R.id.imgLY8);
                //Intent i8 = new Intent();
                //i8.putExtra("AVATAR", avatar);
                //setResult(0, i8);
                //finish();
                break;

            case R.id.imgLY9:
                avatar = String.valueOf(R.id.imgLY9);
                //Intent i9 = new Intent();
                //i9.putExtra("AVATAR", avatar);
                //setResult(0, i9);
                //finish();
                break;

            default:
                throw new RuntimeException("Error: Avatar not avaible!");
        }
            Intent i = new Intent();
            i.putExtra("AVATAR", avatar);
            setResult(0, i);
            finish();
    }
}
