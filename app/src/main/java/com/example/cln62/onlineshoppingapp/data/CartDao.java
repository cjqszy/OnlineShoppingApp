package com.example.cln62.onlineshoppingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cln62.onlineshoppingapp.pojo.DbContract.Entry;
import com.example.cln62.onlineshoppingapp.pojo.Product;

import java.util.ArrayList;
import java.util.List;


public class CartDao implements CartInterface {

    SQLiteDatabase sqLiteDatabase;
    CartDbHelper cartDbHelper;

    public CartDao(Context context) {
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

    @Override
    public List<Product> getCartList() {
        List<Product> list = new ArrayList<>();
        String sql = "select entryid, pname, quantity, price, description, image From "
                + Entry.TABLE_NAME;
//        Cursor result = sqLiteDatabase.rawQuery(sql +"", null);
        Cursor result = sqLiteDatabase.rawQuery(sql, null);
        for(result.moveToFirst(); !result.isAfterLast(); result.moveToNext()){
            Product product = new Product(result.getString(0),
                    result.getString(1),
                    result.getString(2),
                    result.getString(3), // the quantity get here is not the same quantity as that of the product, it is the number in db
                    result.getString(4),
                    result.getString(5));
            list.add(product);
        }
        Log.i("cd", "s");
        return list;
    }

}
