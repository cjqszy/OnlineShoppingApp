package com.example.cln62.onlineshoppingapp.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.cln62.onlineshoppingapp.pojo.SignupProfile;
import com.example.cln62.onlineshoppingapp.ui.signup.SignupActivity;
import com.example.cln62.onlineshoppingapp.utils.AppController;

import java.util.HashMap;
import java.util.Map;

public class NetworkSignUp {

    private static final String TAG = "NetworkSignUp";

    public void signup(final SignupActivity signupActivity, SignupProfile sp) {



        String tag_json_obj = "json_obj_req";
        final SignupProfile profile = sp;

        Log.i(TAG, profile.getFname());
        Log.i(TAG, sp.getFname());

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, response.toString());
                        Log.i(TAG, "request sent");

                        if (response.equals("successfully registered")) {
                            Log.i(TAG, "correct response!");
                            signupActivity.registrtionSuccess();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.i(TAG, "error");
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fname", profile.getFname());
                params.put("lname", profile.getLname());
                params.put("address", profile.getAddress());
                params.put("email", profile.getEmail());
                params.put("mobile", profile.getMobile());
                params.put("password", profile.getPassword());

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
