package com.syncroweb.biocomp20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "Persone";

    public DBHelper(Context context)
    {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

       String q =  "CREATE TABLE " + DBStrings.TBL_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBStrings.FIELD_NAME + " TEXT not null," +
                DBStrings.FIELD_DATE + " TEXT not null," +
                DBStrings.FIELD_PHOTO + "TEXT)";

        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {  }
}
