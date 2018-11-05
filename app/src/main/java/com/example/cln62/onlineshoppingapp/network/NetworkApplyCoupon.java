package com.example.cln62.onlineshoppingapp.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.CatSubcategory;
import com.example.cln62.onlineshoppingapp.ui.checkout.CartActivity;
import com.example.cln62.onlineshoppingapp.utils.AppController;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkApplyCoupon{

    public void getDiscount(final CartActivity cartActivity) {

        MySharedPrefences mySharedPrefences = new MySharedPrefences();
        String apiKey = mySharedPrefences.getApiKey(cartActivity);
        String id = mySharedPrefences.getId(cartActivity);

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/coupon.php?api_key="
                + apiKey + "&user_id=" + id + "&couponno=" + Constants.COUPON_NUMBER;
        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("qaaa", response.toString());
                Log.i("qaaa2", "request sent");
                try {
                    JSONArray jsonArray = response.getJSONArray("Coupon discount");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String couponNumber = jsonObject.getString("couponno");
                        String discount = jsonObject.getString("discount");
                        cartActivity.applyDiscount(discount);
                        Log.i("cj", "request sent");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("qaaa3", "Error: " + error.getMessage());
                Log.i("qaaa4", "error");
            }
        });
        AppController.getInstance().addToRequestQueue(JsonReq, Constants.TAG_JSON_OBJTAG);

    }
}
