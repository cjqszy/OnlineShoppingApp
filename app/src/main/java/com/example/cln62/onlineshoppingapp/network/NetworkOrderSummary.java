package com.example.cln62.onlineshoppingapp.network;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.LoginProfile;
import com.example.cln62.onlineshoppingapp.pojo.OrderSummary;
import com.example.cln62.onlineshoppingapp.ui.checkout.CheckOutActivity;
import com.example.cln62.onlineshoppingapp.ui.checkout.CheckoutFragment;
import com.example.cln62.onlineshoppingapp.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkOrderSummary {

    private static final String TAG = "NetworkOrderSummary";

    public void getOrderSummary(String url, final CheckoutFragment checkoutFragment) {

        Log.i(TAG, url);

        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String orderSummary="";
                try {
/*                    JSONObject jsonObject = response.getJSONObject(0);
                    String orderid = jsonObject.getString("orderid");
                    String orderstatus = jsonObject.getString("orderstatus");
                    String name = jsonObject.getString("name");
                    String billingadd = jsonObject.getString("billingadd");
                    String deliveryadd = jsonObject.getString("deliveryadd");
                    String mobile  = jsonObject.getString("mobile ");
                    String email  = jsonObject.getString("email ");
                    String itemid  = jsonObject.getString("itemid ");
                    String itemname  = jsonObject.getString("itemname ");
                    String itemquantity  = jsonObject.getString("itemquantity ");
                    String totalprice  = jsonObject.getString("totalprice ");
                    String paidprice  = jsonObject.getString("paidprice ");
                    String date  = jsonObject.getString("placedon ");*/
                    JSONArray jsonArray = response.getJSONArray("Order confirmed");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        orderSummary =orderSummary+"orderid: "+jsonObject.getString("orderid")+" \n ";
                        orderSummary =orderSummary+"orderstatus: "+jsonObject.getString("orderstatus")+" \n ";
                        orderSummary =orderSummary+"name: "+jsonObject.getString("name")+" \n ";
                        orderSummary =orderSummary+"billingadd: "+jsonObject.getString("billingadd")+" \n ";
                        orderSummary =orderSummary+"deliveryadd: "+ jsonObject.getString("deliveryadd")+" \n ";
                        orderSummary =orderSummary+"mobile: "+ jsonObject.getString("mobile")+" \n ";
                        orderSummary =orderSummary+"email: "+jsonObject.getString("email")+" \n ";
                        orderSummary =orderSummary+"itemid: "+jsonObject.getString("itemid")+" \n ";
                        orderSummary =orderSummary+"itemname: "+jsonObject.getString("itemname")+" \n ";
                        orderSummary =orderSummary+"itemquantity: "+ jsonObject.getString("itemquantity")+" \n ";
                        orderSummary =orderSummary+"totalprice: "+jsonObject.getString("totalprice")+" \n ";
                        orderSummary =orderSummary+"paidprice: "+ jsonObject.getString("paidprice")+" \n ";
                        orderSummary =orderSummary+"placedon: "+ jsonObject.getString("placedon")+" \n ";
                    }


                    Log.i(TAG, "create summary");
                    checkoutFragment.showOrderSummary(orderSummary);

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
