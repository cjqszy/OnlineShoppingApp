package com.example.cln62.onlineshoppingapp.data;

import android.database.sqlite.SQLiteDatabase;

import com.example.cln62.onlineshoppingapp.pojo.Product;

import java.util.List;

public interface CartInterface {

    void addProduct(Product product);

    List<Product> getCartList();

    void addQuantity(int pid, int quantity);

    void deleteQuantity(int pid, int quantity);

    void removeItem(int pid);

//    String getProductQuantity();

}
