package com.example.cln62.onlineshoppingapp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.Category;
import com.example.cln62.onlineshoppingapp.pojo.SubCategory;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;
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
    private static final String tag_json_obj = Constants.TAG_JSON_OBJTAG;
    List<Category> resList2;
    Context context;

    public ImageLoader(Context context) {
        this.context = context;
    }

    public List<Category> loadCategoryImage(final String userId, final String apiKey) {

        String url = Constants.CATEGORYURL
                + apiKey + Constants.USERID + userId;

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
        });
        AppController.getInstance().addToRequestQueue(JsonReq, tag_json_obj);

        return resList;
    }

    public List<Category> loadSubCategoryImage(final String userId, final String apiKey, String subId) {
        List<Category> resList;

        String url = Constants.SUBCATEGORYURL + subId + Constants.APIKEY
                + apiKey + Constants.USERID + userId;

        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                Log.i(TAG, "request sent");
                try {
                    JSONArray jsonArray = response.getJSONArray("subcategory");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String subCategoryId = jsonObject.getString("scid");
                        String subCaterogyName = jsonObject.getString("scname");
                        String subCaterogyDescription = jsonObject.getString("scdiscription");
                        String subCaterogyImageUrl = jsonObject.getString("scimageurl");
                        resList2 = new ArrayList<>();
                        resList2.add(new Category(subCategoryId, subCaterogyName, subCaterogyDescription, subCaterogyImageUrl));
                        Log.i(TAG, "1 " + String.valueOf(resList2.size()));
                        ((HomeActivity)context).showSubCategory(resList2);
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
        });
        AppController.getInstance().addToRequestQueue(JsonReq, tag_json_obj);

        return resList2;
    }
}
