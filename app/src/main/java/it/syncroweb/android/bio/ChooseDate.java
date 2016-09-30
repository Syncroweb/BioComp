package it.syncroweb.android.bio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class ChooseDate extends AppCompatActivity {


    DatePicker dp;
    String date;

    AdView adsDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsDate = (AdView) findViewById(R.id.adsDate);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsDate.loadAd(adRequest);

        dp = (DatePicker) findViewById(R.id.dpPickADate);

        //Set the datePicker
        assert dp != null;
        dp.setCalendarViewShown(false);

        // DatePicker Listener which build the
        // date string and call a function for
        // a dialog result that confirm or not
        // if the picked date is right
        dp.init(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuilder sb = new StringBuilder();
                date = sb.append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year).toString();
            }
        });
    }

    public void saveDate(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseDate.this);
        builder.setMessage("Confirming the date inserted?" + "\n\n" + date).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    // Listener of the dialog
    // that allows the user to decide
    // if the picked date is right or not
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    String data= date;
                    Intent i = new Intent();
                    i.putExtra("DATE", data);
                    setResult(RESULT_OK, i);
                    dialog.dismiss();
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };


}