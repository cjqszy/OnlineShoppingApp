package com.example.cln62.onlineshoppingapp.network;

import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.OrderHistory;
import com.example.cln62.onlineshoppingapp.ui.profile.PasswordResetActivity;
import com.example.cln62.onlineshoppingapp.utils.AppController;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkResetPassword {

    private static final String TAG = "NetworkResetPassword";

    public void resetPasswordRequest(final PasswordResetActivity passwordResetActivity, String oldPassword, String newPassword,
                                     String mobile) {

        String url = " http://rjtmobile.com/aamir/e-commerce/android-app/shop_reset_pass.php?&mobile="
                + mobile + "&password=" + oldPassword + "&newpassword=" + newPassword;

        Log.i(TAG, url);

        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String message = "";
                try {
                    JSONArray jsonArray = response.getJSONArray("msg");
                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        message = jsonArray.getString(0);
                    }
                    passwordResetActivity.resetResponse(message);
                    Log.i(TAG, "1");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.i(TAG, "error");
                passwordResetActivity.resetResponse(error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(JsonReq, Constants.TAG_JSON_OBJTAG);

    }
}
