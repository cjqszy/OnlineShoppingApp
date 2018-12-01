package com.example.cln62.onlineshoppingapp.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.adapter.TopSellerAdapter;
import com.example.cln62.onlineshoppingapp.network.NetworkTopSeller;
import com.example.cln62.onlineshoppingapp.pojo.TopSeller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopSellerFragment extends Fragment {
    private static final String TAG = "TopSellerFragment";
    private RecyclerView rv_Topseller;
    NetworkTopSeller networkTopSeller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate( R.layout.fragment_top_seller, container, false );
        rv_Topseller = view.findViewById(R.id.rv_TopSeller);

        networkTopSeller = new NetworkTopSeller(this);

        getAsyncTask getTask = new getAsyncTask(networkTopSeller);

        getTask.execute();

        return view;
    }

    public void showTopSeller(List<TopSeller> tsList) {
        TopSellerAdapter adapter = new TopSellerAdapter(getActivity(), tsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_Topseller.setLayoutManager(layoutManager);
        rv_Topseller.setAdapter(adapter);
    }

    private class getAsyncTask extends AsyncTask<Void, Void ,Void>
    {

        private NetworkTopSeller networkTopSeller;

        public getAsyncTask(NetworkTopSeller networkTopSeller) {
            this.networkTopSeller = networkTopSeller;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            networkTopSeller.getTopSeller();
            return null;
        }
    }
}
