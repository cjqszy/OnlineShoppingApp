package com.example.cln62.onlineshoppingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.cln62.onlineshoppingapp.pojo.DbContract.Entry;

public class CartDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shoppingcart.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry._ID + " INTEGER PRIMARY KEY,"
                    + Entry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
                    + Entry.COLUMN_NAME_PNAME + TEXT_TYPE + COMMA_SEP
                    + Entry.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP
                    + Entry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP
                    + Entry.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP
                    + Entry.COLUMN_NAME_QUANTITY + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

    public CartDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}