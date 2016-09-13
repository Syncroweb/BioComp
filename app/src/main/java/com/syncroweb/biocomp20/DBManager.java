package com.syncroweb.biocomp20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;


public class DBManager {

    private DBHelper dbhelper;

    public DBManager(Context ctx)
    {
        dbhelper = new DBHelper(ctx);
    }

    public void save(String name, String date, String photo, Context context)
    {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBStrings.FIELD_NAME, name);
        cv.put(DBStrings.FIELD_DATE, date);
        cv.put(DBStrings.FIELD_PHOTO, photo);

        try
        {
            db.insert(DBStrings.TBL_NAME, null,cv);
        }
        catch (SQLiteException sqle)
        {
            Toast t = new Toast(context);
            t.makeText(context, "Something went wrong with the saving of the datas!", Toast.LENGTH_SHORT);
        }
    }

    public boolean delete(long id)
    {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try
        {
            if (db.delete(DBStrings.TBL_NAME, DBStrings.FIELD_ID+ "=?", new String[]{Long.toString(id)})>0)
                return true;
            return false;
        }
        catch (SQLiteException sqle)
        {
            return false;
        }

    }

    public Cursor query()
    {
        Cursor crs = null;
        try
        {
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            crs = db.query(DBStrings.TBL_NAME, null, null, null, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
            return null;
        }
        return crs;
    }
}
