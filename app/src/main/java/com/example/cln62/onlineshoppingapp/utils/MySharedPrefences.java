package com.example.cln62.onlineshoppingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPrefences {

    public static SharedPreferences getSharePreference(Context context){
        return context.getSharedPreferences( "MySharedPreferenceFile", Context.MODE_PRIVATE );
    }

    public static void setUserInfo(Context context, String id, String firstname,
                                   String lastname, String email, String mobile, String apikey, String password){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putString("id", id);
        editor.putString("firstname", firstname);
        editor.putString("lastname", lastname);
        editor.putString("email", email);
        editor.putString("mobile", mobile);
        editor.putString("apikey", apikey);
        editor.putString("password", password);
        editor.commit();
    }

    public static void updateUserInfo(Context context, String firstname, String lastname, String email, String mobile){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putString("firstname", firstname);
        editor.putString("lastname", lastname);
        editor.putString("email", email);
        editor.putString("mobile", mobile);
        editor.commit();
    }

    public static void clearUserInfo(Context context) {
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putString("id", null);
        editor.putString("firstname", null);
        editor.putString("lastname", null);
        editor.putString("email", null);
        editor.putString("mobile", null);
        editor.putString("apikey", null);
        editor.putString("password", null);
        editor.commit();
    }

    public static String getId(Context context){
        return getSharePreference( context ).getString( "id", null );
    }

    public static String getApiKey(Context context){
        return getSharePreference( context ).getString( "apikey", null );
    }

    public static String getFName(Context context){
        return getSharePreference( context ).getString( "firstname", null );
    }

    public static String getLName(Context context){
        return getSharePreference( context ).getString( "lastname", null );
    }

    public static String getEmail(Context context){
        return getSharePreference( context ).getString( "email", null );
    }

    public static String getMobile(Context context){
        return getSharePreference( context ).getString( "mobile", null );
    }

    public static String getPassword(Context context){
        return getSharePreference( context ).getString( "password", null );
    }

}
