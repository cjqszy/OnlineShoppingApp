package com.example.cln62.onlineshoppingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.pojo.TopSeller;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopSellerAdapter extends RecyclerView.Adapter<TopSellerAdapter.SellerViewHolder> {
    private static final String TAG = "TopSellerAdapter";
    Context context;
    List<TopSeller> topSellers;
    public TopSellerAdapter(Context context, List<TopSeller> topSellers) {
        this.context = context;
        this.topSellers = topSellers;
    }
    @NonNull
    @Override
    public SellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.i( TAG, "onCreateViewHolder: " + position );
        SellerViewHolder holder;
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_seller, parent, false);
        holder = new SellerViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull SellerViewHolder holder, int position) {
        holder.tv_seller_name.setText(topSellers.get(position).getSellername());
        holder.tv_seller_deal.setText(topSellers.get(position).getSellerdeal());
        holder.tv_seller_rating.setText("rating: "+topSellers.get(position).getSellerrating());
        Picasso.with(context).load(topSellers.get(position).getSellerlogo()).into(holder.iv_seller_icon);
    }

    @Override
    public int getItemCount() {
        return topSellers.size();
    }

    class SellerViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_seller_icon;
        private TextView tv_seller_name;
        private TextView tv_seller_deal;
        private TextView tv_seller_rating;
        public SellerViewHolder(View itemView) {
            super(itemView);
            iv_seller_icon = itemView.findViewById(R.id.iv_seller_image);
            tv_seller_name = itemView.findViewById(R.id.tv_seller_name);
            tv_seller_deal = itemView.findViewById(R.id.tv_seller_deal);
            tv_seller_rating = itemView.findViewById(R.id.tv_seller_rating);
        }
    }

}
