package it.syncroweb.android.bio;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calculate extends AppCompatActivity {

    final int CYCLES = 3;
    final int LOAD_OR_NEW_ID = 1161;
    final int RESULT_OK_LIST = 1618;
    final int RESULT_OK_DATE = 1588;

    private String labelTags;

    private double[] vetPhysical = {99.9, 92.3, 81.6, 73.9, 65.2, 56.5, 47.8, 39.1, 30.4, 21.7,
            13, 4.3, 4.3, 13, 21.7, 30.4, 39.1, 47.8, 56.5, 65.2, 73.9,
            81.6, 92.3, 99.9};

    private double[] vetEmotional = {99.9, 93, 86, 79, 71, 64, 57, 50, 43, 36, 29, 21, 14, 7, 0,
            7, 14, 21, 29, 36, 43, 50, 57, 64, 71, 79, 86, 93, 99.9};

    private double[] vetIntellectual = {99.9, 94, 88, 82, 76, 70, 64, 58, 52, 46, 39, 33, 27, 21,
            15, 9, 3, 3, 9, 15, 21, 27, 33, 39, 46, 52, 58, 64, 70,
            76, 82, 88, 94, 99.9};


    private Button avatarOne;
    private Button avatarTwo;
    private TextView lblNameOne;
    private TextView lblNameTwo;
    private Button lblDateOne;
    private Button lblDateTwo;

    private TextView[] vetCalculatedLables = new TextView[CYCLES];

    private Button btnCalculate;
    private TextView result;
    private TextView lblCalculateEm;
    private TextView lblCalculateIn;
    private TextView lblCalculatePh;

    InterstitialAd adsFull;

    RelativeLayout shareView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculate);
        shareView = (RelativeLayout) findViewById(R.id.shareView);

        //Check permission
        checkExternalPermission();

        lblNameOne = (TextView) this.findViewById(R.id.lblNameOne);
        lblNameTwo = (TextView) this.findViewById(R.id.lblNameTwo);
        lblDateOne = (Button) findViewById(R.id.lblDateOne);
        lblDateTwo = (Button) findViewById(R.id.lblDateTwo);
        avatarOne = (Button) findViewById(R.id.btnFirstPhoto);
        avatarTwo = (Button) findViewById(R.id.btnSecondPhoto);

        vetCalculatedLables[0] = (TextView) this.findViewById(R.id.lblCalculatedEm);
        vetCalculatedLables[1] = (TextView) this.findViewById(R.id.lblCalculatedInt);
        vetCalculatedLables[2] = (TextView) this.findViewById(R.id.lblCalculatedPh);

        btnCalculate = (Button) this.findViewById(R.id.btnCalculate);

        result = (TextView) findViewById(R.id.txtResult);
        lblCalculateEm = (TextView) findViewById(R.id.lblCalculatedEm);
        lblCalculateIn = (TextView) findViewById(R.id.lblCalculatedInt);
        lblCalculatePh = (TextView) findViewById(R.id.lblCalculatedPh);

        //Instantiate the InterstitialAd object
        adsFull = new InterstitialAd(this);
        adsFull.setAdUnitId("ca-app-pub-3940256099942544/1033173712");  //id di prova
        requestNewInterstitial();
        adsFull.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });

        //Disable Calculate Button
        assert btnCalculate != null;
        btnCalculate.setEnabled(false);
        btnCalculate.setClickable(false);
        btnCalculate.setTextColor(Color.argb(255, 158, 158, 158));

        // Function that call the real
        // BioCompatibility function and
        // switch between the button
        // "Calculate" and "Reset"
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String buttonTag = btnCalculate.getTag().toString();

                if (buttonTag.equals("calculate")) {
                    calculateBioCompatibility();

                    btnCalculate.setTag("reset");
                    btnCalculate.setText(R.string.reset);

                    //Disable Choose Date Buttons
                    lblDateOne.setEnabled(false);
                    lblDateOne.setClickable(false);
                    lblDateTwo.setEnabled(false);
                    lblDateTwo.setClickable(false);

                    //Disable Avatar Button
                    avatarOne.setEnabled(false);
                    avatarOne.setClickable(false);
                    avatarTwo.setEnabled(false);
                    avatarTwo.setClickable(false);

                } else
                    resetGui();
            }
        });

        // Function that start the activity
        // "calculate_load_or_new" and wait for
        // the user to put the dates
        lblDateOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                labelTags = view.getTag().toString();
                Intent i = new Intent(view.getContext(), CalculateLoadOrNew.class);
                startActivityForResult(i, LOAD_OR_NEW_ID);
            }
        });

        // Function that start the activity
        // "calculate_load_or_new" and wait for
        // the user to put the dates
        lblDateTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                labelTags = view.getTag().toString();
                Intent i = new Intent(view.getContext(), CalculateLoadOrNew.class);
                startActivityForResult(i, LOAD_OR_NEW_ID);
            }
        });
    }

    //Create the AdListener
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("D4DF396F722EC23412033BAED04C6B93")
                .build();

        adsFull.loadAd(adRequest);
    }

    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (adsFull.isLoaded()) {
            adsFull.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOAD_OR_NEW_ID) {
            try {
                switch (resultCode) {
                    case RESULT_OK_DATE:
                        if (labelTags.equals("dateOne")) {
                            lblDateOne.setText(data.getStringExtra("date"));
                        } else if (labelTags.equals("dateTwo")) {
                            lblDateTwo.setText(data.getStringExtra("date"));
                        }
                        break;

                    case RESULT_OK_LIST:
                        String name;
                        if (labelTags.equals("dateOne")) {
                            name = data.getStringExtra("name");
                            lblNameOne.setText(name);
                            lblDateOne.setText(data.getStringExtra("date"));
                            lblDateOne.setClickable(false);
                        } else {
                            lblDateTwo.setText(data.getStringExtra("data"));
                            name = data.getStringExtra("name");
                            lblNameTwo.setText(name);
                            lblDateTwo.setText(data.getStringExtra("date"));
                            lblDateTwo.setClickable(false);
                        }
                        break;
                }

                //Able Calculate Button
                if (!lblDateOne.getText().equals("") && !lblDateTwo.getText().equals("")) {
                    btnCalculate.setEnabled(true);
                    btnCalculate.setClickable(true);
                    btnCalculate.setTextColor(Color.argb(255, 255, 255, 255));
                }
            } catch (Error error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode==0){
            String avatar = data.getStringExtra("AVATAR");
            avatarOne.setBackgroundResource(Integer.parseInt(avatar));

        } else if(requestCode==2){
            String avatar = data.getStringExtra("AVATAR");
            avatarTwo.setBackgroundResource(Integer.parseInt(avatar));
        }

    }

    // Function that calculate the BioCompatibility
    // between the two selected dates
    // It uses several other sub-functions to work
    public void calculateBioCompatibility() {
        Date[] vetDates = new Date[2];

        final int[] vetCostants = {28, 33, 23};
        int[] vetResults = new int[CYCLES];

        int difference;

        try {
            vetDates[0] = DateFormatConverter((String) lblDateOne.getText());
        } catch (Error error) {
            Toast.makeText(getApplicationContext(),
                    error.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        try {
            vetDates[1] = DateFormatConverter((String) lblDateTwo.getText());
        } catch (Error error) {
            Toast.makeText(getApplicationContext(),
                    error.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }


        difference = actualDifference(vetDates[0], vetDates[1]);

        for (int i = 0; i < CYCLES; i++)
            vetResults[i] = difference - ((difference / vetCostants[i]) * vetCostants[i]);

        double fisico = vetPhysical[vetResults[0]]; //Spesso da errore
        double emotivo = vetEmotional[vetResults[1]];
        double intellettuale = vetIntellectual[vetResults[2]];

        vetCalculatedLables[0].setText(Double.toString(fisico) + "%");
        vetCalculatedLables[1].setText(Double.toString(emotivo) + "%");
        vetCalculatedLables[2].setText(Double.toString(intellettuale) + "%");


        writeComments(vetResults);
    }

    // Function that converts the date String
    // into a Date and then returns it
    private Date DateFormatConverter(String myDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //setto la data per italia
        Date myNewDate;

        try {
            myNewDate = dateFormat.parse(myDateString);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
        return myNewDate;
    }

    // Function that calculate the difference
    // between the two selected dates
    public static int actualDifference(Date date1, Date date2) {
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        gc1.setTime(date1);
        gc2.setTime(date2);

        long millis = gc2.getTimeInMillis() - gc1.getTimeInMillis();
        return Math.abs((int) (millis / 1000 / 24 / 60 / 60));
    }

    // Function that displays with TextView
    // the results of the calculations
    public void writeComments(int[] vetResults) {
        String resultMessage;
        String[] messages = new String[CYCLES];


        // Emotional
        messages[0] = getString(R.string.your_result);

        if (vetEmotional[vetResults[0]] < 30)
            messages[0] += this.getString(R.string.emotivo_1) + " ";
        if (vetEmotional[vetResults[0]] > 30 && vetEmotional[vetResults[0]] < 60)
            messages[0] += this.getString(R.string.emotivo_2) + " ";
        if (vetEmotional[vetResults[0]] > 60 && vetEmotional[vetResults[0]] < 80)
            messages[0] += this.getString(R.string.emotivo_3) + " ";
        if (vetEmotional[vetResults[0]] > 80)
            messages[0] += this.getString(R.string.emotivo_4) + " ";

        // Intellectual
        messages[1] = "";

        if (vetIntellectual[vetResults[1]] < 30)
            messages[1] = this.getString(R.string.intellettuale_1) + " ";
        if (vetIntellectual[vetResults[1]] > 30 && vetIntellectual[vetResults[0]] < 60)
            messages[1] = this.getString(R.string.intellettuale_2) + " ";
        if (vetIntellectual[vetResults[1]] > 60 && vetIntellectual[vetResults[0]] < 80)
            messages[1] = this.getString(R.string.intellettuale_3) + " ";
        if (vetIntellectual[vetResults[1]] > 80)
            messages[1] = this.getString(R.string.intellettuale_4) + " ";

        // Physical
        messages[2] = "";

        if (vetPhysical[vetResults[2]] < 30)
            messages[2] = this.getString(R.string.fisico_1);
        if (vetPhysical[vetResults[2]] > 30 && vetPhysical[vetResults[0]] < 60)
            messages[2] = this.getString(R.string.fisico_2);
        if (vetPhysical[vetResults[2]] > 60 && vetPhysical[vetResults[0]] < 80)
            messages[2] = this.getString(R.string.fisico_3);
        if (vetPhysical[vetResults[2]] > 80)
            messages[2] = this.getString(R.string.fisico_4);

        resultMessage = messages[0] + messages[1] + messages[2];

        //Results
        result.setText(resultMessage);
    }

    // Function that resets the GUI in order to
    // make it ready to be used again
    public void resetGui() {
        for (int i = 0; i < CYCLES; i++)
            vetCalculatedLables[i].setText("");

        lblDateOne.setText(R.string.date);
        lblDateOne.setClickable(true);

        lblDateTwo.setText(R.string.date);
        lblDateTwo.setClickable(true);

        result.setText("");

        //Disable Calculate Button
        btnCalculate.setText(R.string.calculate);
        btnCalculate.setTag("calculate");
        btnCalculate.setEnabled(false);
        btnCalculate.setClickable(false);

        //Able Choose Date Buttons
        lblDateOne.setText(R.string.date);
        lblDateTwo.setText(R.string.date);
        lblDateOne.setEnabled(true);
        lblDateOne.setClickable(true);
        lblDateTwo.setEnabled(true);
        lblDateTwo.setClickable(true);

        //Able Avatar Button
        avatarOne.setEnabled(true);
        avatarOne.setClickable(true);
        avatarTwo.setEnabled(true);
        avatarTwo.setClickable(true);

        //Percentual
        lblCalculateEm.setText("0%");
        lblCalculateIn.setText("0%");
        lblCalculatePh.setText("0%");
        lblCalculateEm.setTextColor(Color.argb(255, 213, 0, 0));
        lblCalculateIn.setTextColor(Color.argb(255, 213, 0, 0));
        lblCalculatePh.setTextColor(Color.argb(255, 213, 0, 0));
    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                    inImage, "", "");
            return Uri.parse(path);
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }


    //Gestione del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculate, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuReset:
                resetGui();
                break;

            case R.id.actionShare:

                try {
                    Bitmap bitmap = getBitmapFromView(shareView);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(this, bitmap));
                    shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.result_bio)); //frase già precompilata per il commento
                    shareIntent.setType("image/jpeg");
                    //shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);   //PROBLEMA PERMESSO DA API 23
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send)));

                }catch (Exception e){
                    e.getMessage();
                }

                break;

        }
        return false;
    }


    //Choose avatar first photo
    public void chooseAvatarOne(View view) {
        Intent i = new Intent(this, ChooseAvatar.class);
        startActivityForResult(i, 0);
    }

    //Choose avatar second photo
    public void chooseAvatarTwo(View view) {
        Intent i = new Intent(this, ChooseAvatar.class);
        startActivityForResult(i, 2);
    }


    //PERMESSI CONDIVISIONE
    public static final int MY_PERMISSIONS_REQUEST_W_EXTERNAL = 99;

    private void checkExternalPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Write External Permission Needed")
                        .setMessage("This app needs the write external permission, please accept to use share functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Calculate.this,
                                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_W_EXTERNAL);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_W_EXTERNAL);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_W_EXTERNAL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {


                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
