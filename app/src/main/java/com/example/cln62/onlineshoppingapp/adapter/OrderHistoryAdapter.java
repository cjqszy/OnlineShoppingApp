package com.example.cln62.onlineshoppingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.pojo.OrderHistory;

import java.util.List;


public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.HistoryViewHolder> {
    private Context context;
    private List<OrderHistory> orderHistoryItems;


    public OrderHistoryAdapter(Context context, List<OrderHistory> OrderHistoryItem) {
        this.context = context;
        this.orderHistoryItems = OrderHistoryItem;
    }


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_history, viewGroup, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.tv_id.setText("order ID: " + orderHistoryItems.get(position).getOrderId());
        holder.tv_time.setText("order Time: " + orderHistoryItems.get(position).getOrderTime());
        holder.tv_price.setText("Price: " + orderHistoryItems.get(position).getOrderPrice());
        holder.tv_name.setText("Name: " + orderHistoryItems.get(position).getOrderName());
        holder.tv_bads.setText("Address: " + orderHistoryItems.get(position).getOrderBas());
        holder.tv_mobile.setText("Mobile: " + orderHistoryItems.get(position).getOrderMobile());
        holder.tv_tracking.setText("Shipment Id: " + orderHistoryItems.get(position).getShipmentId());
        holder.tv_tracking_status.setText("Shipment Status: " + getStatus(position));
    }

    private String getStatus(int position) {

        int statusCode = Integer.valueOf(orderHistoryItems.get(position).getShipmentTracking());

        String status = "";

         switch (statusCode) {
            case 1:
                status = "Order Confirm";
                break;
            case 2:
                status = "Order Dispatch";
                break;
            case 3:
                status = "Order On the Way";
                break;
            case 4:
                status = "Order Delivered";
                break;
        }

        return status;
    }

    @Override
    public int getItemCount() {
        return orderHistoryItems.size();
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private TextView tv_time;
        private TextView tv_price;
        private TextView tv_name;
        private TextView tv_bads;
        private TextView tv_mobile;
        private TextView tv_tracking;
        private TextView tv_tracking_status;

        public HistoryViewHolder(View itemView) {
            super( itemView );

            tv_id = itemView.findViewById(R.id.tv_history_id);
            tv_time = itemView.findViewById(R.id.tv_history_time);
            tv_price = itemView.findViewById(R.id.tv_history_price);
            tv_name = itemView.findViewById(R.id.tv_history_name);
            tv_bads = itemView.findViewById(R.id.tv_history_billingaddress);
            tv_mobile = itemView.findViewById(R.id.tv_history_mobile);
            tv_tracking = itemView.findViewById(R.id.textview_tracking);
            tv_tracking_status = itemView.findViewById(R.id.textview_tracking_status);
        }
    }

}
