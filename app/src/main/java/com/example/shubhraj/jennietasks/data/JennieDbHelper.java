package com.example.shubhraj.jennietasks.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.COLUMN_DATE;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.COLUMN_DESCRIPTION;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.COLUMN_DONE;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.TABLE_NAME;

/**
 * Created by Shubhraj on 25-10-2017.
 */

public class JennieDbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "jennietasks.db";
    public static final int DATABASE_VERISON = 1;

    public JennieDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERISON);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                JennieContract.JennieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DATE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                COLUMN_DONE + " INTEGER);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
