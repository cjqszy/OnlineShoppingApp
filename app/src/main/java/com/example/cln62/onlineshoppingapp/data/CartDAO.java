package com.example.cln62.onlineshoppingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        int quantity = 0;
        String productId = product.getId();
        String sqlCheckQuantity = "SELECT quantity FROM " + Entry.TABLE_NAME
                + " WHERE entryid = " + productId;
        Cursor result = sqLiteDatabase.rawQuery(sqlCheckQuantity, null);

        for(result.moveToFirst(); !result.isAfterLast(); result.moveToNext()){

            quantity = result.getInt(0);
        }

        if (quantity == 0) {
            ContentValues values = new ContentValues();
            values.put(Entry.COLUMN_NAME_ENTRY_ID, product.getId());
            values.put(Entry.COLUMN_NAME_PNAME, product.getPname());
            values.put(Entry.COLUMN_NAME_PRICE, product.getPrize());
            values.put(Entry.COLUMN_NAME_DESCRIPTION, product.getDescription());
            values.put(Entry.COLUMN_NAME_IMAGE, product.getImage());
            values.put(Entry.COLUMN_NAME_QUANTITY, 1);

            sqLiteDatabase.insert(Entry.TABLE_NAME, null, values );
            Log.i("db1", product.getPname() );
            return;
        }

        quantity++;
        String sqlUpdateQuantity = "UPDATE " + Entry.TABLE_NAME + " SET quantity = "
                + quantity + " WHERE entryid =" + " " + productId;
        sqLiteDatabase.execSQL(sqlUpdateQuantity);

        Log.i("db2", product.getPname() + " " + quantity);

        result.close();
    }

}
