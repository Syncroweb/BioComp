package com.syncroweb.biocomp20;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class RegisteredDateList extends AppCompatActivity {

    ListView lwPersone;

    private CursorAdapter adapter;
    DBManager db;
    private Cursor cursor;

    Button btnAddNewDate;

    //As soon as the GUI is created
    //this function will fill the
    //GridView with the records
    //from the database, allowing, then, the user
    //to pick one and modify / load it
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_date_list);

        lwPersone = (ListView)findViewById(R.id.lwPersone);
        btnAddNewDate = (Button)findViewById(R.id.btnAddNewDate);
        db = new DBManager(this);
        Cursor crs = db.query();

        adapter = new CursorAdapter(this, crs, false)
        {
            @Override
            public View newView(Context ctx, Cursor arg1, ViewGroup arg2)
            {
                View v = getLayoutInflater().inflate(R.layout.activity_registered_date_list, null);
                return v;
            }

            @Override
            public void bindView(View v, Context arg1, Cursor crs)
            {
                String name = crs.getString(crs.getColumnIndex(DBStrings.FIELD_NAME));
                String date = crs.getString(crs.getColumnIndex(DBStrings.FIELD_DATE));
                String tmp = crs.getString(crs.getColumnIndex(DBStrings.FIELD_PHOTO));

                byte[] blobAsBytes = tmp.getBytes();
                Bitmap bmp = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);
            }

            @Override
            public long getItemId(int position)
            {
                Cursor crs = adapter.getCursor();
                crs.moveToPosition(position);
                return crs.getLong(crs.getColumnIndex(DBStrings.FIELD_ID));
            }
        };

        lwPersone.setAdapter(adapter);

        //Start the activity that will allow
        //the user to create a new record in
        //the DB
        btnAddNewDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RegisterOrLoadDate.class);
                startActivity(i);
            }
        });

    }
}
