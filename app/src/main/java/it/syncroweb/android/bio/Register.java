package it.syncroweb.android.bio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Register extends AppCompatActivity {

    EditText txtName;
    ImageButton btnAvatar;
    DatePicker dpDate;
    String name;
    String date;
    int id;

    AdView adsRegister;
    String avatar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = (EditText) findViewById(R.id.txtNameSurname);
        dpDate = (DatePicker) findViewById(R.id.datePicker);
        btnAvatar = (ImageButton) findViewById(R.id.avatar);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");

        //Banner
        adsRegister = (AdView) findViewById(R.id.adsRegister);
        AdRequest adRequest = new AdRequest.Builder().build();
        adsRegister.loadAd(adRequest);


        //Set the DatePicker
        assert dpDate != null;
        dpDate.setCalendarViewShown(false);

         //Listener of the DatePicker
        // that build the date string once
        // the user choosen the date he wants
        dpDate.init(dpDate.getYear(), dpDate.getMonth(), dpDate.getDayOfMonth(), new DatePicker.OnDateChangedListener()
        {
            public void onDateChanged(DatePicker view, int dayOfMonth, int monthOfYear, int year)
            {
                StringBuilder sb = new StringBuilder();
                date = sb.append(dayOfMonth).append("/").append(monthOfYear+1).append("/").append(year).toString().trim();
            }
        });

    }

    //Choose an avatar
    public void chooseAvatar(View view) {
        Intent i= new Intent(this, ChooseAvatar.class);
        startActivityForResult(i, 0);
    }

    //Assegnare ID


    // Call Back method to get the Strings form other Activity
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0){
            avatar = data.getStringExtra("AVATAR");
            btnAvatar.setBackgroundResource(Integer.parseInt(avatar));
        }
    }

    //GESTIONE ACTION BAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_register, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idItem = item.getItemId();
        switch (idItem) {

            case R.id.actionDone:

                name = txtName.getText().toString().trim();

                if (!name.equals("") && !date.equals("") && !avatar.equals("")) {
                    Intent i = new Intent();
                    i.putExtra("ID", id);
                    i.putExtra("AVATAR", avatar);
                    i.putExtra("NAME", name);
                    i.putExtra("DATE", date);
                    setResult(0, i);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.insert_register,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return false;
    }
}
