package it.syncroweb.android.bio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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
    AdView adsChooseAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);

        MobileAds.initialize(getApplicationContext(), String.valueOf(R.string.prova_banner));

        //Banner
        adsChooseAvatar= (AdView) findViewById(R.id.adsChooseAvatar);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsChooseAvatar.loadAd(adRequest);


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
                avatar = String.valueOf(R.drawable.girl);
                break;

            case R.id.imgLY2:
                avatar = String.valueOf(R.drawable.girl2);
                break;

            case R.id.imgLY3:
                avatar = String.valueOf(R.drawable.girl3);
                break;

            case R.id.imgLY4:
                avatar = String.valueOf(R.drawable.man);
                break;

            case R.id.imgLY5:
                avatar = String.valueOf(R.drawable.man2);
                break;

            case R.id.imgLY6:
                avatar = String.valueOf(R.drawable.man3);
                break;

            case R.id.imgLY7:
                avatar = String.valueOf(R.drawable.girl4);
                break;

            case R.id.imgLY8:
                avatar = String.valueOf(R.drawable.man4);
                break;

            case R.id.imgLY9:
                avatar = String.valueOf(R.drawable.girl5);
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
