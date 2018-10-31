package com.example.cln62.onlineshoppingapp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cln62.onlineshoppingapp.pojo.LoginProfile;
import com.example.cln62.onlineshoppingapp.ui.login.LoginActivity;
import com.example.cln62.onlineshoppingapp.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkLogIn {

    Context context;

    public NetworkLogIn(Context context) {
        this.context = context;
    }

    private static final String TAG = "NetworkLogIn";

    public void login(String mb, String pwd, final LoginActivity loginActivity) {


        String tag_json_obj = "json_obj_req";
        final String mobile = mb;
        final String password = pwd;
//        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php";

        String url =  "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?mobile="
                + mobile + "&password=" + password;

        JsonArrayRequest JsonReq = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, response.toString());
                Log.i(TAG, "request sent");
                String firstname = "";
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String id = jsonObject.getString("id");
                    firstname = jsonObject.getString("firstname");
                    String lastname = jsonObject.getString("lastname");
                    String email = jsonObject.getString("email");
                    String mobile = jsonObject.getString("mobile");
                    String appapikey  = jsonObject.getString("appapikey ");
                    Log.i("aaa", id);
                    LoginProfile loginProfile = new LoginProfile(id, firstname, lastname, email, mobile, appapikey);
                    loginActivity.loginSuccess(loginProfile);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.i(TAG, "error");
                Log.i(TAG, mobile);
                Log.i(TAG, password);
            }
        });

        AppController.getInstance().addToRequestQueue(JsonReq, tag_json_obj);
    }


}
