package com.example.cln62.onlineshoppingapp.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.pojo.Category;
import com.example.cln62.onlineshoppingapp.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageLoader {

    private static final String TAG = "ImageLoader";

    public List<Category> loadImage(final String userId, final String apiKey) {
//        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php";
        String tag_json_obj = "json_obj_req";

        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key="
                + apiKey + "&user_id=" + userId;

        Log.i("aaa4", userId);
        Log.i("aaa4", apiKey);
        Log.i("aaa4", url);

        final List<Category> resList = new ArrayList<>();
        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                Log.i(TAG, "request sent");
                try {
                    JSONArray jsonArray = response.getJSONArray("category");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String categoryId = jsonObject.getString("cid");
                        String caterogyName = jsonObject.getString("cname");
                        String caterogyDescription = jsonObject.getString("cdiscription");
                        String caterogyImageUrl = jsonObject.getString("cimagerl");
                        resList.add(new Category(categoryId, caterogyName, caterogyDescription, caterogyImageUrl));
                    }
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
        })
/*        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", userId);
                params.put("api_key", apiKey);
                return params;
            }

        }*/;

        AppController.getInstance().addToRequestQueue(JsonReq, tag_json_obj);

        return resList;
    }
}
