package com.syncroweb.biocomp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView splash;
    Animation animazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash= (ImageView) findViewById(R.id.imgSplash);
        animazione= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movimento);
        splash.startAnimation(animazione);

        Thread cambiaPagina = new Thread() {

            public void run() {
                try {
                    int tempo = 0;
                    while (tempo < 4000) {
                        sleep(100);
                        tempo += 100;
                    }
                    Intent i = new Intent(SplashScreen.this, MainMenu.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        cambiaPagina.start();
    }

   /* public void goToMenu(View view) {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }*/
}
