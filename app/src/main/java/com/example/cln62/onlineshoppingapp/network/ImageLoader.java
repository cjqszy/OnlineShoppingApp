package com.example.cln62.onlineshoppingapp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.CatSubcategory;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.ui.home.HomeActivity;
import com.example.cln62.onlineshoppingapp.ui.home.HomeFragment;
import com.example.cln62.onlineshoppingapp.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageLoader {

    private static final String TAG = "ImageLoader";
    private static final String tag_json_obj = Constants.TAG_JSON_OBJTAG;
    List<CatSubcategory> resList2;
    Context context;
    HomeFragment homeFragment;

    public ImageLoader(Context context, HomeFragment homeFragment) {
        this.context = context;
        this.homeFragment = homeFragment;
    }

    public void loadCategoryImage(final String userId, final String apiKey) {

        String url = Constants.CATEGORYURL
                + apiKey + Constants.USERID + userId;

        Log.i("aaa4", userId);
        Log.i("aaa4", apiKey);
        Log.i("aaa4", url);

//        final List<CatSubcategory> resList = new ArrayList<>();
        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                Log.i(TAG, "request sent");
                List<CatSubcategory> resList = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("category");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String categoryId = jsonObject.getString("cid");
                        String caterogyName = jsonObject.getString("cname");
                        String caterogyDescription = jsonObject.getString("cdiscription");
                        String caterogyImageUrl = jsonObject.getString("cimagerl");
                        resList.add(new CatSubcategory(categoryId, caterogyName, caterogyDescription, caterogyImageUrl));
                    }
                    homeFragment.initRecyclerViewFinished(resList);
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

//        return resList;
    }

    public List<CatSubcategory> loadSubCategoryImage(final String userId, final String apiKey, final String cid) {
        List<CatSubcategory> resList;

        String url = Constants.SUBCATEGORYURL + cid + Constants.APIKEY
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
                        resList2.add(new CatSubcategory(subCategoryId, subCaterogyName, subCaterogyDescription, subCaterogyImageUrl));
                        Log.i(TAG, "1 " + String.valueOf(resList2.size()));
                        ((HomeActivity)context).showSubCategory(resList2, cid);
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

    public void loadProductList(String userId, String apiKey, String cid, String scid) {
        String url = Constants.PRODUCTURL + cid + "&scid=" + scid + Constants.APIKEY
                + apiKey + Constants.USERID + userId;

        Log.i("4", url);

        JsonObjectRequest JsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                Log.i(TAG, "request sent");
                try {
                    JSONArray jsonArray = response.getJSONArray("products");
                    List<Product> resList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String productId = jsonObject.getString("id");
                        String pname = jsonObject.getString("pname");
                        String quantity = jsonObject.getString("quantity");
                        String price = jsonObject.getString("prize");
                        String description = jsonObject.getString("discription");
                        String image = jsonObject.getString("image");

                        resList.add(new Product(productId, pname, quantity, price, description, image));
                    }
                    ((HomeActivity)context).showProductList(resList);
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
    }
}
