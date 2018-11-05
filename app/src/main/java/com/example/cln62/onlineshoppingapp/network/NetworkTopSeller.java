package com.example.cln62.onlineshoppingapp.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.TopSeller;
import com.example.cln62.onlineshoppingapp.ui.home.TopSellerFragment;
import com.example.cln62.onlineshoppingapp.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkTopSeller {

    TopSellerFragment topSellerFragment;
    private static final String TAG = "NetworkTopSeller";

    public NetworkTopSeller(TopSellerFragment topSellerFragment) {
        this.topSellerFragment = topSellerFragment;
    }

    public void getTopSeller() {

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_top_sellers.php?";

        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<TopSeller> tsList = new ArrayList<TopSeller>();
                try {
                    JSONArray jsonArray = response.getJSONArray("Top sellers");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String sellername = jsonObject.getString("sellername");
                        String sellerdeal = jsonObject.getString("sellerdeal");
                        String sellerrating = jsonObject.getString("sellerrating");
                        String sellerlogo = jsonObject.getString("sellerlogo");
                        tsList.add(new TopSeller(id, sellername, sellerdeal, sellerrating, sellerlogo));
                    }
                    topSellerFragment.showTopSeller(tsList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.i(TAG, "error");

            }
        });

        AppController.getInstance().addToRequestQueue(JsonReq, Constants.TAG_JSON_OBJTAG);
    }
}
