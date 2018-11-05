package com.example.cln62.onlineshoppingapp.network;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.OrderHistory;
import com.example.cln62.onlineshoppingapp.ui.history.OrderHistoryFragment;
import com.example.cln62.onlineshoppingapp.utils.AppController;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkShipmentTracking {

    MySharedPrefences mySharedPrefences;
    private static final String TAG = "NetworkShipmentTracking";

    public void addShipmentTracking(FragmentActivity activity, final OrderHistoryFragment orderHistoryFragment, final List<OrderHistory> mList) {
        mySharedPrefences = new MySharedPrefences();

        if (mList == null || mList.size() == 0) {
            return;
        }

        List<OrderHistory> newList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            final String order_id = mList.get(i).getOrderId();
            String api_key = mySharedPrefences.getApiKey(activity);
            String user_id = mySharedPrefences.getId(activity);

            String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shipment_track.php?api_key="
                    + api_key + "&user_id=" + user_id + "&order_id=" + order_id;

            JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
//                    List<OrderHistory> newList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = response.getJSONArray("Shipment track");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String shipmentid = jsonObject.getString("shipmentid");
                            String shipmentstatus = jsonObject.getString("shipmentstatus");

                            OrderHistory orderHistory = new OrderHistory(order_id, mList.get(i).getOrderTime(), mList.get(i).getOrderPrice(),
                                    mList.get(i).getOrderName(), mList.get(i).getOrderBas(), mList.get(i).getOrderMobile(), shipmentid, shipmentstatus);
/*                            mList.get(i).setShipmentId(shipmentid);
                            mList.get(i).setShipmentTracking(shipmentstatus)
  /*                          newList.add(new OrderHistory(order_id, mList.get(i).getOrderTime(), mList.get(i).getOrderPrice(),
                                    mList.get(i).getOrderName(), mList.get(i).getOrderBas(), mList.get(i).getOrderMobile(), shipmentid, shipmentstatus));*/
/*                            mList.get(i).setShipmentId(shipmentid);
                            mList.get(i).setShipmentTracking(shipmentstatus);*/
                            orderHistoryFragment.ShipmentTrackingAdded(orderHistory);
                        }

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
}
