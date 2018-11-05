package com.example.cln62.onlineshoppingapp.network;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.OrderHistory;
import com.example.cln62.onlineshoppingapp.ui.history.OrderHistoryActivity;
import com.example.cln62.onlineshoppingapp.ui.history.OrderHistoryFragment;
import com.example.cln62.onlineshoppingapp.utils.AppController;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkOrderHistory {

    private static final String TAG = "NetworkOrderHistory";
    OrderHistoryFragment orderHistoryFragment;

    public NetworkOrderHistory(OrderHistoryActivity activity) {
//        orderHistoryActivity = activity;
    }

    public NetworkOrderHistory(OrderHistoryFragment orderHistoryFragment) {
        this.orderHistoryFragment = orderHistoryFragment;
    }

    public void getOrderHistory() {

        MySharedPrefences mySharedPrefences = new MySharedPrefences();

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/order_history.php?" +
                "api_key=" + mySharedPrefences.getApiKey(orderHistoryFragment.getContext()) +
                "&user_id=" + mySharedPrefences.getId(orderHistoryFragment.getContext()) +
                "&mobile=" +mySharedPrefences.getMobile(orderHistoryFragment.getContext());

        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                String orderHistory="";
                List<OrderHistory> mList = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("Order history");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String orderId = jsonObject.getString("orderid");
                        String orderTime = jsonObject.getString("placedon");
                        String orderPrice = jsonObject.getString("paidprice");
                        String orderName = jsonObject.getString("name");
                        String orderBas = jsonObject.getString("billingadd");
                        String orderMobile = jsonObject.getString("mobile");

                        mList.add(new OrderHistory(orderId, orderTime, orderPrice, orderName, orderBas, orderMobile, null, null));
                    }


                    Log.i(TAG, "create history");
                    orderHistoryFragment.addShipmentTracking(mList);
//                    orderHistoryFragment.showOrderHistory(mList);

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
