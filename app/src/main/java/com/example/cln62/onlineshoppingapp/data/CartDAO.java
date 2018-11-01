package com.example.cln62.onlineshoppingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cln62.onlineshoppingapp.pojo.DbContract.Entry;
import com.example.cln62.onlineshoppingapp.pojo.Product;

public class CartDAO implements CartInterface {

    SQLiteDatabase sqLiteDatabase;
    CartDbHelper cartDbHelper;

    public CartDAO(Context context) {
        cartDbHelper = new CartDbHelper(context);
        openDb();
    }

    private void openDb() {
        sqLiteDatabase = cartDbHelper.getWritableDatabase();
    }

    public void closeDb(){
        sqLiteDatabase.close();
    }

    @Override
    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_NAME_ENTRY_ID, product.getId());
        values.put(Entry.COLUMN_NAME_PNAME, product.getPname());
        values.put(Entry.COLUMN_NAME_PRICE, product.getPrize());
        values.put(Entry.COLUMN_NAME_DESCRIPTION, product.getDescription());
        values.put(Entry.COLUMN_NAME_IMAGE, product.getImage());
        values.put(Entry.COLUMN_NAME_QUANTITY, product.getQuantity());

        sqLiteDatabase.insert(Entry.TABLE_NAME, null, values );
        Log.i("db", product.getPname());
    }
}
